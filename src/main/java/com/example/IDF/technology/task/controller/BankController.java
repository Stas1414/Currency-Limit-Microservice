package com.example.IDF.technology.task.controller;

import com.example.IDF.technology.task.entity.Transaction;
import com.example.IDF.technology.task.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class BankController {

    private final TransactionService transactionService;

    @Autowired
    public BankController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/process")
    public ResponseEntity<Transaction> processTransaction(@RequestBody Transaction transaction) {
        Transaction processedTransaction = transactionService.getTransaction(transaction);
        return ResponseEntity.ok(processedTransaction);
    }

}
