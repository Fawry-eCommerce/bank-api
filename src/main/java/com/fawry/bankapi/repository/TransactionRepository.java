package com.fawry.bankapi.repository;

import com.fawry.bankapi.model.Account;
import com.fawry.bankapi.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository <Transaction,Long> {
    Page<Transaction> findByAccount(Account Account, Pageable pageable);

}
