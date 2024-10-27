package com.fawry.bankapi.service;


import com.fawry.bankapi.dto.AccountDTO;
import com.fawry.bankapi.dto.AccountRequest;
import com.fawry.bankapi.dto.LoginRequest;
import com.fawry.bankapi.mapper.AccountMapper;
import com.fawry.bankapi.model.Account;
import com.fawry.bankapi.model.AccountStatus;
import com.fawry.bankapi.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountDTO createAccount(AccountRequest accountRequest) {
        Account account = Account.builder()
                .cardNum(generateUniqueCardNum())
                .name(accountRequest.getName())
                .password(accountRequest.getPassword())
                .CVV(generateCVV())
                .balance(0)
                .status(AccountStatus.ACTIVE)
                .build();
        return accountMapper.toAccountDTO(accountRepository.save(account));
    }

    private String generateUniqueCardNum() {
        String cardNumber;
        do {
            cardNumber = generateNumber(16);
        } while (accountRepository.findByCardNum(cardNumber) != null);
        return cardNumber;
    }

    private String generateCVV() {
        return generateNumber(3);
    }

    private String generateNumber(int len) {
        StringBuilder number = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }

    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Account not found with id: " + accountId)
                );
    }

    public AccountDTO getAccountById(Long accountId) {
        return accountMapper.toAccountDTO(getAccount(accountId));
    }

    public AccountDTO findByCardNumber(String cardNum) {
        return accountMapper.toAccountDTO(accountRepository.findByCardNum(cardNum));
    }

    public boolean login(LoginRequest loginRequest) {
        Account account = accountRepository.findByCardNumAndPassword(loginRequest.getCardNum(), loginRequest.getPassword())
                .orElseThrow(
                        () -> new EntityNotFoundException("Invalid credentials")
                );
        return true;
    }

    public void updateBalance(Account account, float amount) {
        account.setBalance(amount);
        accountRepository.save(account);
    }

    public float getAccountBalance(Long accountId) {
        Account account = getAccount(accountId);
        return account.getBalance();
    }
}
