package com.example.IDF.technology.task.service;

import com.example.IDF.technology.task.entity.ExchangeRate;
import com.example.IDF.technology.task.feign.ExchangeRateClient;
import com.example.IDF.technology.task.repository.ExchangeRateRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRateService {

    @Value("${api.twelve.data.key}")
    private String apiKey;

    private final ExchangeRateRepository exchangeRateRepository;

    private final ExchangeRateClient exchangeRateClient;

    @Autowired
    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository, ExchangeRateClient exchangeRateClient) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateClient = exchangeRateClient;
    }

}
