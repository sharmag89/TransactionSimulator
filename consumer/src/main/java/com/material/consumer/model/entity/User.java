package com.material.consumer.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    private Integer userId;

    private String firstName;
    private String lastName;

    private boolean isActive;
    private LocalDateTime insTime;
    private LocalDateTime updTime;
}
