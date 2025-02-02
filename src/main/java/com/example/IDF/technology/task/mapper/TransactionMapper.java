package com.example.IDF.technology.task.mapper;

import com.example.IDF.technology.task.dto.TransactionDto;
import com.example.IDF.technology.task.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionMapper {

    Transaction transactionDtoToTransaction(TransactionDto transactionDto);

    TransactionDto transactionToTransactionDto(Transaction transaction);
}
