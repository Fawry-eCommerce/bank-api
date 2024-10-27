package com.fawry.bankapi.repository;

import com.fawry.bankapi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByCardNum(String cardNum);
    Optional<Account> findByCardNumAndPassword(String cardNum, String password);
}

