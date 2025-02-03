package com.example.IDF.technology.task.controller;

import com.example.IDF.technology.task.dto.ExceededTransactionDto;
import com.example.IDF.technology.task.entity.Transaction;
import com.example.IDF.technology.task.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class BankController {

    private static final Logger logger = LoggerFactory.getLogger(BankController.class);
    private final TransactionService transactionService;

    @Autowired
    public BankController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/process")
    public ResponseEntity<Transaction> processTransaction(@RequestBody Transaction transaction) {
        logger.info("Processing transaction: {}", transaction);
        Transaction processedTransaction = transactionService.getTransaction(transaction);
        logger.info("Transaction processed: {}", processedTransaction);
        return ResponseEntity.ok(processedTransaction);
    }

    @GetMapping("/limit_exceeded")
    public ResponseEntity<List<ExceededTransactionDto>> getLimitExceededTransactions() {
        logger.info("Getting limit exceeded transactions");
        List<ExceededTransactionDto> transactionLimitExceededReported = transactionService.getTransactionLimitExceededReported();
        return ResponseEntity.ok(transactionLimitExceededReported);
    }
}
