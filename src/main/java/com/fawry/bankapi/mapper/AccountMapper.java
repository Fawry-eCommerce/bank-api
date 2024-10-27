package com.fawry.bankapi.mapper;

import com.fawry.bankapi.dto.AccountDTO;
import com.fawry.bankapi.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDTO toAccountDTO(Account account);
}
