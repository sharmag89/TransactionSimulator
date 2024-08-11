package com.material.shared.dto;

import com.material.shared.enums.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class TransactionLogDto {
    private UUID transactionId;
    private String cardNumber;
    private Integer merchantId;
    private TransactionType transactionType;
    private Double amount;
    private short retryCount;
}
