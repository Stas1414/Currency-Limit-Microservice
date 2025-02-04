package com.example.IDF.technology.task.mapperImpl;

import com.example.IDF.technology.task.dto.TransactionDto;
import com.example.IDF.technology.task.entity.Transaction;
import com.example.IDF.technology.task.mapper.TransactionMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Implementation of the {@link TransactionMapper} interface, providing conversion between
 * {@link Transaction} and {@link TransactionDto} objects.
 * <p>
 * This component is used for mapping data between layers of the application, such as
 * converting transaction entity data to DTO format and vice versa.
 * </p>
 */
@Component
public class TransactionMapperImpl implements TransactionMapper {

    /**
     * Converts a {@link TransactionDto} object to a {@link Transaction}.
     * <p>
     * If the provided {@link TransactionDto} object is {@code null}, {@code null} is returned.
     * The date for the {@link Transaction} object is set to the current time.
     * </p>
     *
     * @param transactionDto The object containing transaction data in DTO format.
     * @return A {@link Transaction} object corresponding to the data in {@link TransactionDto},
     *         or {@code null} if the input object is {@code null}.
     */
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

    /**
     * Converts a {@link Transaction} object to a {@link TransactionDto}.
     * <p>
     * If the provided {@link Transaction} object is {@code null}, {@code null} is returned.
     * </p>
     *
     * @param transaction The object containing transaction data in entity format.
     * @return A {@link TransactionDto} object corresponding to the data in {@link Transaction},
     *         or {@code null} if the input object is {@code null}.
     */
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
