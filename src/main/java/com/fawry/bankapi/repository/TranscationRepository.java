package com.fawry.bankapi.repository;

import com.fawry.bankapi.model.Account;
import com.fawry.bankapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TranscationRepository extends JpaRepository <Transaction,Integer> {
    List<Transaction> findByAccount(Account Account);

}
