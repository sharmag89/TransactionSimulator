package com.material.consumer.model.entity;

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
    private String card_number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "card_type_id")
    private CardType cardType;

    @Column(nullable = false)
    private Double cardLimit;

    @Column(nullable = false)
    private Double cardBalance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userID;

    private boolean isActive;
    private LocalDateTime insTime;
    private LocalDateTime updTime;
}
