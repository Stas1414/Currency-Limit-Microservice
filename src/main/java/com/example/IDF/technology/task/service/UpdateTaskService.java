package com.example.IDF.technology.task.service;

import com.example.IDF.technology.task.entity.ExchangeRate;
import com.example.IDF.technology.task.feign.ExchangeRateClient;
import com.example.IDF.technology.task.repository.ExchangeRateRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class UpdateTaskService {

    @Value("${api.twelvedata.key}")
    private String apiKey;

    private final ExchangeRateClient exchangeRateClient;

    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public UpdateTaskService(ExchangeRateClient exchangeRateClient, ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateClient = exchangeRateClient;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Scheduled( cron = "0 30 11 * * ?" )
    public void updateTask() {
        ExchangeRate exchangeRateKZT = exchangeRateClient.getExchangeRate("USD/KZT", "1day", apiKey);
        ExchangeRate exchangeRateRUB = exchangeRateClient.getExchangeRate("USD/RUB", "1day", apiKey);
        exchangeRateRepository.saveAll(List.of(exchangeRateKZT, exchangeRateRUB));
    }


}
