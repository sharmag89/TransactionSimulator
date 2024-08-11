package com.material.consumer.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Merchant {
    @Id
    @GeneratedValue
    private Integer merchantId;

    private String firstName;
    private String lastName;

    private boolean isActive;
    private LocalDateTime insTime;
    private LocalDateTime updTime;

}
