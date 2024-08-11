package com.material.shared.enums;

import java.util.Random;

public enum TransactionType {
    DEBIT,
    CREDIT;

    private static final Random random = new Random();

    public static TransactionType randomTransaction()  {
        TransactionType[] transactionTypes = values();
        return transactionTypes[random.nextInt(transactionTypes.length)];
    }
}
