package com.fawry.bankapi.controller;

import com.fawry.bankapi.dto.*;
import com.fawry.bankapi.model.Transaction;
import com.fawry.bankapi.service.AccountService;
import com.fawry.bankapi.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class BankController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createAccount(@Valid @RequestBody AccountRequest accountRequest) {
        return accountService.createAccount(accountRequest);
    }

    @PostMapping("login")
    public Long login(@Valid @RequestBody LoginRequest loginRequest) {
        return accountService.login(loginRequest);
    }

    @GetMapping("account/{accountId}/balance")
    public float getAccountBalance(@PathVariable Long accountId) {
        return accountService.getAccountBalance(accountId);
    }

    @PostMapping("transactions")
    public Long createTransaction(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.createTransaction(transactionRequest).getTransactionId();
    }

    @PostMapping("transactions/{accountId}")
    public Page<Transaction> getTransactionsForAccount(Pageable pageable, @PathVariable Long accountId) {
        return transactionService.getTransactionsForAccount(pageable, accountId);
    }

}
