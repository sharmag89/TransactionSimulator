package com.material.consumer.model.entity;

import com.material.shared.enums.AuthorizationStatus;
import com.material.shared.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @CreationTimestamp
    @Column(name = "ins_time")
    private LocalDateTime insTime;

    @UpdateTimestamp
    @Column(name = "upd_time")
    private LocalDateTime updTime;
}
