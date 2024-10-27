package com.fawry.bankapi.dto;

import com.fawry.bankapi.model.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionRequest {
    @NotNull(message = "Account id required")
    private Long accountId;
    @NotNull(message = "Transaction type required")
    private TransactionType type;
    @NotNull(message = "Amount required")
    private float amount;
}
