package com.material.authorization.model.entity;

import com.material.shared.enums.AuthorizationStatus;
import com.material.shared.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"transactionId"})
public class TransactionLog {
    @Id
    @Column(name = "transaction_id")
    private UUID transactionId;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private Integer merchantId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(nullable = false)
    private Double amount;

    private Boolean isExecuted;

    @Enumerated(EnumType.STRING)
    private AuthorizationStatus authorizationStatus;

    private LocalDateTime insTime;
    private LocalDateTime updTime;
}
