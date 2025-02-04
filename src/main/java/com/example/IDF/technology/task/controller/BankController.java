package com.example.IDF.technology.task.controller;

import com.example.IDF.technology.task.dto.ExceededTransactionDto;
import com.example.IDF.technology.task.entity.Transaction;
import com.example.IDF.technology.task.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Controller for managing bank transactions.
 * Provides endpoints for processing transactions and retrieving transactions that exceed set limits.
 */
@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Bank Transactions", description = "API for managing bank transactions")
public class  BankController {

    private static final Logger logger = LoggerFactory.getLogger(BankController.class);
    private final TransactionService transactionService;

    /**
     * Constructor to initialize the controller with a {@link TransactionService} instance.
     *
     * @param transactionService the service responsible for transaction operations
     */
    @Autowired
    public BankController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Processes a given transaction.
     * The transaction will be validated and processed by the {@link TransactionService}.
     *
     * @param transaction the transaction object to be processed
     * @return a {@link ResponseEntity} with the processed transaction
     */
    @Operation(summary = "Process a transaction", description = "Accepts a transaction and processes it.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction successfully processed"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/process")
    public ResponseEntity<Transaction> processTransaction(@RequestBody Transaction transaction) {
        logger.info("Processing transaction: {}", transaction);
        Transaction processedTransaction = transactionService.getTransaction(transaction);
        logger.info("Transaction processed: {}", processedTransaction);
        return ResponseEntity.ok(processedTransaction);
    }

    /**
     * Retrieves a list of transactions that have exceeded the set limit.
     *
     * @return a {@link ResponseEntity} containing the list of exceeded transactions
     */
    @Operation(summary = "Get list of transactions exceeding the limit", description = "Returns a list of transactions that have exceeded the set limit.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/limit_exceeded")
    public ResponseEntity<List<ExceededTransactionDto>> getLimitExceededTransactions() {
        logger.info("Getting limit exceeded transactions");
        List<ExceededTransactionDto> transactionLimitExceededReported = transactionService.getTransactionLimitExceededReported();
        return ResponseEntity.ok(transactionLimitExceededReported);
    }
}
