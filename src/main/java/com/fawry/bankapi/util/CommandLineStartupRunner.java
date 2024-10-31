package com.fawry.bankapi.util;

import com.fawry.bankapi.model.Account;
import com.fawry.bankapi.model.AccountStatus;
import com.fawry.bankapi.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandLineStartupRunner implements CommandLineRunner {

    private final AccountRepository accountRepository;

    @Override
    public void run(String... args) {
        addMerchantAccount();
    }

    void addMerchantAccount() {
        if (accountRepository.findByCardNum("1234567890123456") != null) {
            return;
        }

        accountRepository.save(Account.builder()
                .cardNum("1234567890123456")
                .name("Fawry System")
                .password("123")
                .CVV("123")
                .balance(150000)
                .status(AccountStatus.ACTIVE)
                .build());
    }
}
