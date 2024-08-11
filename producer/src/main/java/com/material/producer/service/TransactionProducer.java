package com.material.producer.service;

import com.material.producer.client.DetailsFeignClient;

import com.material.producer.config.Constants;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.material.shared.dto.TransactionLogDto;
import com.material.shared.dto.TransactionRequestDto;
import com.material.shared.enums.Topic;
import com.material.shared.enums.TransactionType;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionProducer {
    private KafkaTemplate<String, TransactionLogDto> kafkaTemplate;
    private DetailsFeignClient detailsFeignClient;
    private Constants constants;

    @SneakyThrows
    @Observed(name = "producer.request",
            contextualName = "producer-request",
            lowCardinalityKeyValues = {"requestType", "autoProduce"})
    public void autoProduce(int messageCount, long timeInSeconds) {
        log.info("Starting auto produce with <{}> Messages",messageCount);

        URI authServiceUrl = URI.create(constants.authServicePath);
        List<String> allCardNumbers = detailsFeignClient.listAllCards(authServiceUrl);
        List<Integer> allMerchantIds = detailsFeignClient.listAllMerchants(authServiceUrl);
        if(allCardNumbers.isEmpty() || allMerchantIds.isEmpty()) {
            log.info("Cards of Merchants list empty. Returning.");
            return;
        }
        long waitTime = (timeInSeconds*1000/messageCount);
        Random rand = new Random();
        while(messageCount != 0) {

            String randomCardNumber =  allCardNumbers.get(rand.nextInt(allCardNumbers.size()));
            Integer randomMerchantId = allMerchantIds.get(rand.nextInt(allMerchantIds.size()));

            sendMessage(
                    new TransactionLogDto(
                            new TransactionRequestDto(randomCardNumber,
                                    randomMerchantId,
                                    TransactionType.randomTransaction(),
                                    rand.nextDouble(0.1, 4000))));
            messageCount--;
            Thread.sleep(waitTime);
        }
    }

    public void publish(TransactionRequestDto transactionRequestDto) {
        TransactionLogDto transactionLogDto = new TransactionLogDto(transactionRequestDto);
        sendMessage(transactionLogDto);
    }

   // @Transactional()
    public void sendMessage(TransactionLogDto msg) {
        try {
            kafkaTemplate.send(Topic.TXN, msg.getCardNumber(), msg);
            log.info("Kafka Message sent with : "+msg.toString());
        } catch (Exception ex) {
               // LOGGER.error(ex.getMessage(), ex);
            System.out.println("Exception Occured " +ex.getMessage());
                //throw new TransferServiceException(ex);
        }
    }
}
