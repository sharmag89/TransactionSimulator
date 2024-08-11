package com.material.authorization.service;

import com.material.shared.enums.AuthorizationStatus;
import com.material.shared.enums.TransactionType;
import com.material.shared.dto.AuthorizationResponseDto;
import com.material.shared.dto.TransactionLogDto;
import com.material.authorization.model.entity.Card;
import com.material.authorization.model.entity.Merchant;
import com.material.authorization.repository.CardRepository;
import com.material.authorization.repository.MerchantRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AuthorizationService {
    private final CardRepository cardRepository;
    private final MerchantRepository merchantRepository;

    public AuthorizationResponseDto authorizeTransaction(TransactionLogDto transactionLogDto) {
        AuthorizationResponseDto authorizationResponseDto = new AuthorizationResponseDto();
        authorizationResponseDto.setTransactionId(transactionLogDto.getTransactionId());
        log.info("Begin Auth for txnId :"+transactionLogDto.getTransactionId());
        Optional<Card> card = cardRepository.findById(transactionLogDto.getCardNumber());
        Optional<Merchant> merchant = merchantRepository.findById(transactionLogDto.getMerchantId());
        AuthorizationStatus status;

        status = validateTransactionAmount(card, merchant, transactionLogDto);
        authorizationResponseDto.setAuthorizationStatus(status);
//        if(card.isPresent() && status ==  AuthorizationStatus.AUTHORIZED) {
////            executeTransactionInDB(card.get(), transactionLogDto, authorizationResponseDto);
//        }
        log.info("End Auth for txnId :"+transactionLogDto.getTransactionId());
        return authorizationResponseDto;
    }

    private AuthorizationStatus validateTransactionAmount(Optional<Card> card, Optional<Merchant> merchant, TransactionLogDto transactionLogDto) {
        if(card.isEmpty() || !card.get().isActive()) {
            log.error("Error : Card is not active : "+transactionLogDto.getTransactionId());
            return AuthorizationStatus.UNAUTHORIZED;
        } else if(merchant.isEmpty() || !merchant.get().isActive()) {
            return AuthorizationStatus.MERCHANT_UNAUTHORIZED;
        }
        if(transactionLogDto.getAmount() > card.get().getCardType().getDefaultTransactionLimit()) {
            log.error("Error : Amount exceeds transaction limit : "+transactionLogDto.getTransactionId());
            return AuthorizationStatus.UNAUTHORIZED;
        }
        if(card.get().getCardBalance() < transactionLogDto.getAmount()) {
            return AuthorizationStatus.MONTHLY_LIMIT_EXCEED;
        }
        return AuthorizationStatus.AUTHORIZED;
    }

//    @Transactional
//    private void executeTransactionInDB(Card card, TransactionLogDto transactionLogDto, AuthorizationResponseDto authorizationResponseDto) {
//        if(transactionLogDto.getTransactionType() == TransactionType.CREDIT) {
//            card.setCardBalance(card.getCardBalance()+ transactionLogDto.getAmount());
//        } else {
//            card.setCardBalance(card.getCardBalance()- transactionLogDto.getAmount());
//        }
//        try {
//            cardRepository.save(card);
//        }catch (HibernateException ex) {
//           log.error("Hibernate Exception Occured!"+ex.getMessage());
//            authorizationResponseDto.setAuthorizationStatus(AuthorizationStatus.ERROR);
//        }
//        authorizationResponseDto.setAuthorizationStatus(AuthorizationStatus.AUTHORIZED);
//    }
}
