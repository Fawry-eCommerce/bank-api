package com.fawry.bankapi.service;


import com.fawry.bankapi.model.Account;
import com.fawry.bankapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    public Account createAccount(String cardNum, String name, String password, int cvv, float balance, String status) {
        Account account = new Account();
        account.setCardNum(cardNum);
        account.setName(name);
        account.setPassword(password);
        account.setCVV(cvv);
        account.setBalance(balance);
        account.setStatus(status);
        return accountRepository.save(account);
    }
    public Optional<Account> getAccountById(int accountId) {
        return accountRepository.findById(accountId);
    }

    public Account findByCardNumber(String cardNum) {
        return accountRepository.findByCardNum(cardNum);
    }

    public boolean login(String cardNum, String password) {
        Account account = accountRepository.findByCardNum(cardNum);
        return account != null && account.getPassword().equals(password);
    }

    public void updateBalance(Account account, float amount) {
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

    }


}
