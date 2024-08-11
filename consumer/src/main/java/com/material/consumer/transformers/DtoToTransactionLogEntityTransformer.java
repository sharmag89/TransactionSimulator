package com.material.consumer.transformers;

import com.material.shared.dto.TransactionLogDto;
import com.material.consumer.model.entity.TransactionLog;
import org.springframework.stereotype.Component;

@Component
public class DtoToTransactionLogEntityTransformer {
    public TransactionLog apply(final TransactionLogDto transactionLogDto) {
        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setCardNumber(transactionLogDto.getCardNumber());
        transactionLog.setMerchantId(transactionLogDto.getMerchantId());
        transactionLog.setAmount(transactionLogDto.getAmount());
        transactionLog.setTransactionType(transactionLogDto.getTransactionType());
        transactionLog.setTransactionId(transactionLogDto.getTransactionId());
        transactionLog.setAuthorizationStatus(transactionLogDto.getAuthorizationStatus());

        return transactionLog;
    }
}
