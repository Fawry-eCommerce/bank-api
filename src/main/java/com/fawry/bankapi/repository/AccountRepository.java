package com.fawry.bankapi.repository;

import com.fawry.bankapi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByCardNum(String cardNum);

}

