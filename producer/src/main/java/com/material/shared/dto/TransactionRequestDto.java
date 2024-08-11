package com.material.shared.dto;


import com.material.shared.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionRequestDto {
    private String cardNumber;
    private Integer merchantId;
    private TransactionType transactionType;
    private Double amount;
}
