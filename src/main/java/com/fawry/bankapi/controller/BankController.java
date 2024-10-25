package com.fawry.bankapi.controller;

import com.fawry.bankapi.dto.AccountDTO;
import com.fawry.bankapi.model.Account;
import com.fawry.bankapi.model.Transaction;
import com.fawry.bankapi.service.AccountService;
import com.fawry.bankapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/account")
    public ResponseEntity<AccountDTO> createAccount(@RequestParam String cardNumber,
                                                    @RequestParam String name,
                                                    @RequestParam String password,
                                                    @RequestParam int cvv,
                                                    @RequestParam(defaultValue = "0.0") float balance,
                                                    @RequestParam String status) {
        AccountDTO account = accountService.createAccount(cardNumber, name, password, cvv, balance, status);
        return ResponseEntity.ok(account);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestParam String cardNum, @RequestParam String password) {
        boolean isLoggedIn = accountService.login(cardNum, password);
        return isLoggedIn ? ResponseEntity.ok("Login successful") : ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> createTransaction(@RequestParam Long accountId,
                                                         @RequestParam String type,
                                                         @RequestParam float amount,
                                                         @RequestParam String details) {
        Transaction transaction = transactionService.createTransaction(accountId, type, amount, details);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/account/{accountId}/balance")
    public ResponseEntity<Float> getAccountBalance(@PathVariable Long accountId) {
        Account account = accountService.getAccountById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        return ResponseEntity.ok(account.getBalance());
    }

    @PostMapping("/account/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsForAccount(@PathVariable Long accountId) {
        List<Transaction> transactions = transactionService.getTransactionsForAccount(accountId);
        return ResponseEntity.ok(transactions);
    }


}
