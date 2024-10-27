package com.fawry.bankapi.mapper;

import com.fawry.bankapi.dto.TransactionDto;
import com.fawry.bankapi.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDto toTransactionDto(Transaction transaction);
}
