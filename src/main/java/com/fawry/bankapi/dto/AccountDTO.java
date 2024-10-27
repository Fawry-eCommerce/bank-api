package com.fawry.bankapi.dto;

import com.fawry.bankapi.model.AccountStatus;
import lombok.Data;

@Data
public class AccountDTO {
    private Long accountId;
    private String cardNum;
    private String name;
    private float balance;
    private AccountStatus status;
}
