package com.example.IDF.technology.task.mapper;

import com.example.IDF.technology.task.dto.TransactionDto;
import com.example.IDF.technology.task.entity.Transaction;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link Transaction} and {@link TransactionDto} objects.
 *
 * This interface is used for mapping data from one object to another. Specifically,
 * for converting {@link TransactionDto} objects to {@link Transaction} and vice versa.
 * This is useful when transferring data between different layers of the application,
 * such as the presentation layer and the business logic layer.
 *
 * MapStruct automatically generates the implementation of this interface during the compilation phase,
 * ensuring efficient and safe conversion between types.
 */
@Mapper
public interface TransactionMapper {

    /**
     * Converts a {@link TransactionDto} object to a {@link Transaction} entity.
     *
     * @param transactionDto The object containing transaction data in DTO format.
     * @return A {@link Transaction} object corresponding to the data in {@link TransactionDto}.
     */
    Transaction transactionDtoToTransaction(TransactionDto transactionDto);

    /**
     * Converts a {@link Transaction} entity to a {@link TransactionDto} object.
     *
     * @param transaction The object containing transaction data in entity format.
     * @return A {@link TransactionDto} object corresponding to the data in {@link Transaction}.
     */
    TransactionDto transactionToTransactionDto(Transaction transaction);
}
