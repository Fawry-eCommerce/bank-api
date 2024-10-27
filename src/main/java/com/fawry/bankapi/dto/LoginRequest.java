package com.fawry.bankapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Card number is required")
    private String cardNum;
    @NotBlank(message = "Password is required")
    private String password;
}
