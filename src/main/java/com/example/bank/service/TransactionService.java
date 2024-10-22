package com.example.bank.service;

import com.example.bank.model.Account;
import com.example.bank.model.Transaction;
import com.example.bank.repository.TranscationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TranscationRepository transcationRepository;

    @Autowired
    private AccountService accountService;

    public Transaction createTransaction(int accountId, String type, float amount, String details) {
        Account account = accountService.getAccountById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));

        if (type.equals("withdraw") && account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        // Update account balance
        float newBalance = type.equals("deposit") ? account.getBalance() + amount : account.getBalance() - amount;
        accountService.updateBalance(account, newBalance);

        // Create transaction
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setTransactionType(type);
        transaction.setAmount(amount);
        transaction.setDetails(details);
        transaction.setCreatedAt(LocalDateTime.now());

        return transcationRepository.save(transaction);

    }
    public List<Transaction> getTransactionsForAccount(int accountId) {
        Account account = accountService.getAccountById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        return transcationRepository.findByAccount(account);
    }
}
