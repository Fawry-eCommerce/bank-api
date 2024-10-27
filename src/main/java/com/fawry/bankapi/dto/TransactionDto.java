package com.fawry.bankapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDto {
    private Long transactionId ;
    private AccountDTO account;
    private String transactionType;
    private float amount;
    private String details;
    private LocalDateTime createdAt;
}
