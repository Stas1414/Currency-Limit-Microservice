package com.example.IDF.technology.task.service;

import com.example.IDF.technology.task.entity.ExchangeRate;
import com.example.IDF.technology.task.entity.Transaction;
import com.example.IDF.technology.task.feign.ExchangeRateClient;
import com.example.IDF.technology.task.repository.ExchangeRateRepository;
import com.example.IDF.technology.task.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Value("${57b52341185b4c23bb0e8f9c819887cd}")
    private String apiKey;

    private final TransactionRepository transactionRepository;

    private final ExchangeRateRepository exchangeRateRepository;

    private final ExchangeRateClient exchangeRateClient;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, ExchangeRateRepository exchangeRateRepository, ExchangeRateClient exchangeRateClient) {
        this.transactionRepository = transactionRepository;
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateClient = exchangeRateClient;
    }

}
