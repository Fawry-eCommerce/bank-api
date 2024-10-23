package com.fawry.bankapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId ;

    @ManyToOne
    @JoinColumn(name = "accountId",nullable = false)
    private Account account;

    @Column(nullable = false)
    private String transactionType;

    @Column(nullable = false)
    private float amount;

    @Column()
    private String details;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}
