package com.fawry.bankapi.service;

import com.fawry.bankapi.dto.TransactionDto;
import com.fawry.bankapi.dto.TransactionRequest;
import com.fawry.bankapi.exception.InsufficientBalanceException;
import com.fawry.bankapi.mapper.TransactionMapper;
import com.fawry.bankapi.model.Account;
import com.fawry.bankapi.model.Transaction;
import com.fawry.bankapi.model.TransactionType;
import com.fawry.bankapi.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final TransactionMapper transactionMapper;

    public TransactionDto createTransaction(TransactionRequest transactionRequest) {
        Account account = accountService.getAccount(transactionRequest.getAccountId());

        TransactionType type = transactionRequest.getType();

        if (type == TransactionType.WITHDRAW && account.getBalance() < transactionRequest.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        // Update account balance
        float newBalance = type == TransactionType.DEPOSIT
                ? account.getBalance() + transactionRequest.getAmount()
                : account.getBalance() - transactionRequest.getAmount();
        accountService.updateBalance(account, newBalance);

        String details = "Account number " + account.getCardNum() + " has been " + type.name().toLowerCase() + "ed with " + transactionRequest.getAmount() + " EGP";

        // Create transaction
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setTransactionType(type);
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setDetails(details);
        transaction.setCreatedAt(LocalDateTime.now());


        TransactionDto transactionDto = transactionMapper.toTransactionDto(transactionRepository.save(transaction));
        transactionDto.setNewBalance(newBalance);
        return transactionDto;


    }

    public Page<Transaction> getTransactionsForAccount(Pageable pageable, Long accountId) {
        Account account = accountService.getAccount(accountId);
        return transactionRepository.findByAccount(account, pageable);
    }
}
