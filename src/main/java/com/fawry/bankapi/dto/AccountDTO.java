package com.fawry.bankapi.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private Long accountId;
    private String name;
    private float balance;
    private String status;


}
