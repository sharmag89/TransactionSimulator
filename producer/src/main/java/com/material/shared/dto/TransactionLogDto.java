package com.material.shared.dto;


import com.material.shared.enums.AuthorizationStatus;
import com.material.shared.enums.TransactionType;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionLogDto {
    private UUID transactionId;
    @NonNull
    private String cardNumber;
    @NonNull
    private Integer merchantId;
    @NonNull
    private TransactionType transactionType;
    @NonNull
    private Double amount;
    private AuthorizationStatus authorizationStatus;

    private short retryCount;
    private Double amountWithdrawnToday;

    public TransactionLogDto(TransactionRequestDto transactionRequestDto) {
        this.transactionId = UUID.randomUUID();
        this.cardNumber = transactionRequestDto.getCardNumber();
        this.merchantId = transactionRequestDto.getMerchantId();
        this.transactionType = transactionRequestDto.getTransactionType();
        this.amount = transactionRequestDto.getAmount();
        this.authorizationStatus = AuthorizationStatus.INFLIGHT;
    }


}
