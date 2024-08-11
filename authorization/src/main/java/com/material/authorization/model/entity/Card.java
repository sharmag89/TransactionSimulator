package com.material.authorization.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Cards")
@NoArgsConstructor
public class Card {
    @Id
    @NonNull
    private String cardNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "card_type_id")
    private CardType cardType;

    private Double cardLimit;
    private Double cardBalance;

    private boolean isActive;
    private LocalDateTime insTime;
    private LocalDateTime updTime;
}
