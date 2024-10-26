package com.fawry.bankapi.service;


import com.fawry.bankapi.dto.AccountDTO;
import com.fawry.bankapi.mapper.AccountMapper;
import com.fawry.bankapi.model.Account;
import com.fawry.bankapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;
    public AccountDTO createAccount(String cardNum, String name, String password, int cvv, float balance, String status) {
        Account account = new Account();
        account.setCardNum(generateUniqueCardNum());
        account.setName(name);
        account.setPassword(password);
        account.setCVV(cvv);
        account.setBalance(balance);
        account.setStatus(status);
        return accountMapper.toAccountDTO(accountRepository.save(account));
    }

    // Method to generate a unique 16-digit card number
    private String generateUniqueCardNum() {
        String cardNum;
        Random random = new Random();
        do {
            StringBuilder cardNumBuilder = new StringBuilder(16);
            for (int i = 0; i < 16; i++) {
                cardNumBuilder.append(random.nextInt(10)); // Append random digit (0-9)
            }
            cardNum = cardNumBuilder.toString();
        } while (accountRepository.findByCardNum(cardNum) != null); // Ensure uniqueness
        return cardNum;
    }

    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    public AccountDTO  findByCardNumber(String cardNum) {
        return accountMapper.toAccountDTO(accountRepository.findByCardNum(cardNum));
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
