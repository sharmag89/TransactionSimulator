package com.material.authorization.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class CardType {

    @Id
    @GeneratedValue
    private Byte cardTypeId;

    private String name;

    private Double defaultTransactionLimit;
    private Double defaultDailyTransactionLimit;

    private LocalDateTime insTime;
    private LocalDateTime updTime;
}
