package com.material.consumer.services;

import com.material.consumer.client.AuthorizationFeignClient;
import com.material.consumer.config.Constants;
import com.material.consumer.exceptions.ErrorCode;
import com.material.consumer.exceptions.ServiceException;
import com.material.consumer.model.entity.Card;
import com.material.consumer.repositories.CardRepository;
import com.material.shared.enums.AuthorizationStatus;
import com.material.shared.enums.Topic;
import com.material.shared.dto.AuthorizationResponseDto;
import com.material.shared.dto.TransactionLogDto;
import com.material.consumer.model.entity.TransactionLog;
import com.material.consumer.repositories.TransactionLogRepository;
import com.material.consumer.transformers.DtoToTransactionLogEntityTransformer;
import com.material.shared.enums.TransactionType;
import feign.RetryableException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionListner {
    private final AuthorizationFeignClient authorizationFeignClient;
    private final TransactionLogRepository transactionLogRepository;
    private final CardRepository cardRepository;
    private final DtoToTransactionLogEntityTransformer dtoToTransactionLogEntityTransformer;
    private final Constants constants;
    @RetryableTopic(
            attempts = "3",
            backoff = @Backoff(delay = 1000, multiplier = 3.0),
            autoCreateTopics = "true",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE)
    @KafkaListener(id = Topic.TXN, topics = Topic.TXN, groupId = "user-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void processMessage(TransactionLogDto transactionLogDto, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("Message Received : "+transactionLogDto.toString()+" from Topic : "+topic);
        if(transactionLogDto.getTransactionId() == null || transactionLogDto.getCardNumber() == null
                || transactionLogDto.getAmount() == null || transactionLogDto.getMerchantId() == null) {
            return;
        }
        log.info("Begin Processing : ");

        TransactionLog transactionLog = dtoToTransactionLogEntityTransformer.apply(transactionLogDto);
        if(transactionLogDto.getAuthorizationStatus().equals(AuthorizationStatus.INFLIGHT)) {
            initiateTransaction(transactionLog);
        }

        log.info("Authorizing Transaction : "+transactionLog.toString());
        AuthorizationStatus authorizationStatus = transactionLog.getAuthorizationStatus();
        //ack the transaction, commit offset
        //acknowledgment.acknowledge();

        //transactionLogDto.setAmountWithdrawnToday(transactionLogRepository.amountWithdrawnToday(transactionLog.getCardNumber(), LocalDateTime.now()));
        if(authorizationStatus.equals(AuthorizationStatus.IN_PROGRESS)) {
            try {
                URI authServiceUrl = URI.create(constants.authServicePath);
                AuthorizationResponseDto authorizationResponseDto = authorizationFeignClient.authorizeTransaction(authServiceUrl,transactionLogDto);
                log.info("Authorization Result : " + authorizationResponseDto.toString());
                authorizationStatus = authorizationResponseDto.getAuthorizationStatus();
            }catch (RetryableException ex) {
                throw ex;
            }
        }

        if(authorizationStatus.equals(AuthorizationStatus.AUTHORIZED)) {
            executeTransaction(transactionLogDto, authorizationStatus);
        }

        if(authorizationStatus.equals(AuthorizationStatus.IN_PROGRESS)
                || authorizationStatus.equals(AuthorizationStatus.ERROR)) {

                //retry message
                throw new ServiceException("Message not processed successfully. Rertry!", ErrorCode.SERVICE_NOT_REACHABLE);
                //if retry count > 3
                //send message to deadLetterQueue
        } else {
            //Commit the transaction
            commitTransaction(transactionLog, authorizationStatus);
        }
    }

    @DltHandler
    public void dlt(TransactionLogDto transactionLogDto, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("Event from topic "+topic+" is dead lettered - event:" + transactionLogDto.toString());
    }

    @Transactional
    public void initiateTransaction(TransactionLog transactionLog) {
        transactionLog.setIsExecuted(false);
        transactionLog.setAuthorizationStatus(AuthorizationStatus.IN_PROGRESS);
        transactionLogRepository.save(transactionLog);
    }

    @Transactional
    public void commitTransaction(TransactionLog transactionLog, AuthorizationStatus authorizationStatus) {
        if(authorizationStatus.equals(AuthorizationStatus.EXECUTED)) {
            transactionLog.setIsExecuted(true);
        }
        transactionLog.setAuthorizationStatus(authorizationStatus);
        transactionLogRepository.save(transactionLog);
    }

    @Transactional
    private void executeTransaction(TransactionLogDto transactionLogDto, AuthorizationStatus authorizationStatus) {
        Card card = cardRepository.findById(transactionLogDto.getCardNumber()).orElseThrow();

        if(transactionLogDto.getTransactionType() == TransactionType.CREDIT) {
            card.setCardBalance(card.getCardBalance()+ transactionLogDto.getAmount());
        } else {
            card.setCardBalance(card.getCardBalance()- transactionLogDto.getAmount());
        }
        try {
            cardRepository.save(card);
            transactionLogDto.setAuthorizationStatus(AuthorizationStatus.EXECUTED);
        }catch (HibernateException ex) {
            log.error("Hibernate Exception Occured!"+ex.getMessage());
            transactionLogDto.setAuthorizationStatus(AuthorizationStatus.ERROR);
        }

//        authorizationResponseDto.setAuthorizationStatus(AuthorizationStatus.AUTHORIZED);
    }

}
