package com.example.IDF.technology.task.mapperImpl;

import com.example.IDF.technology.task.dto.TransactionDto;
import com.example.IDF.technology.task.entity.Transaction;
import com.example.IDF.technology.task.mapper.TransactionMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionMapperImpl implements TransactionMapper {
    @Override
    public Transaction transactionDtoToTransaction(TransactionDto transactionDto) {

        if (transactionDto == null) {
            return null;
        }
        return new Transaction(transactionDto.getAccountFrom(),
                transactionDto.getAccountTo(),
                LocalDateTime.now(),
                transactionDto.getAmount(),
                transactionDto.getCurrency(),
                transactionDto.getCategory());
    }

    @Override
    public TransactionDto transactionToTransactionDto(Transaction transaction) {
         if (transaction == null) {
             return null;
         }

         return new TransactionDto(transaction.getAccountFrom(),
                 transaction.getAccountTo(),
                 transaction.getDate(),
                 transaction.getAmount(),
                 transaction.getCurrency(),
                 transaction.getCategory());
    }
}
