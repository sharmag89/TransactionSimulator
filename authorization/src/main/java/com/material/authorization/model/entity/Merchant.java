package com.material.authorization.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
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
