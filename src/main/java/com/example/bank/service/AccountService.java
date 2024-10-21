package com.example.bank.service;

import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    public Account createAccount(String cardNum, String name, int cvv, float balance, String status) {
        Account account = new Account();
        account.setCardNum(cardNum);
        account.setName(name);
        account.setCVV(cvv);
        account.setBalance(balance);
        account.setStatus(status);
        return accountRepository.save(account);
    }
    public Optional<Account> getAccountById(int accountId) {
        return accountRepository.findById(accountId);
    }

    public Account findByCardNumber(String cardNumber) {
        return accountRepository.findBycardNumber(cardNumber);
    }

    public boolean login(String cardNumber, String password) {
        Account account = accountRepository.findBycardNumber(cardNumber);
        return account != null && account.getPassword().equals(password);
    }

    public void updateBalance(Account account, float amount) {
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

    }


}