package com.example.IDF.technology.task.service;

import java.util.logging.Logger;

import com.example.IDF.technology.task.entity.ExchangeRate;
import com.example.IDF.technology.task.feign.ExchangeRateClient;
import com.example.IDF.technology.task.mapperImpl.ForexRateMapperImpl;
import com.example.IDF.technology.task.model.ForexRate;
import com.example.IDF.technology.task.repository.ExchangeRateRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

/**
 * ForexService class that updates exchange rates periodically by fetching them from an external API.
 */
@Service
public class ForexService {

    private static final Logger logger = Logger.getLogger(ForexService.class.getName());


    @Value("${api.twelve.data.key}")
    private String apiKey;

    private final ExchangeRateClient exchangeRateClient;
    private final ExchangeRateRepository exchangeRateRepository;
    private final ForexRateMapperImpl forexRateMapper;

    /**
     * Constructor injection for required dependencies.
     *
     * @param exchangeRateClient     the client to fetch exchange rates
     * @param exchangeRateRepository the repository to save exchange rates
     * @param forexRateMapper        the mapper to convert forex rate to entity
     */
    @Autowired
    public ForexService(ExchangeRateClient exchangeRateClient, ExchangeRateRepository exchangeRateRepository, ForexRateMapperImpl forexRateMapper) {
        this.exchangeRateClient = exchangeRateClient;
        this.exchangeRateRepository = exchangeRateRepository;
        this.forexRateMapper = forexRateMapper;
    }

    /**
     * Scheduled method that runs daily at 11:30 AM to update exchange rates.
     * It fetches rates for USD/KZT and USD/RUB, maps them to entities, and saves them to the database.
     */
    @PostConstruct
    @Scheduled(cron = "0 30 11 * * ?")
    public void updateTask() {
        logger.info("Updating exchange rates...");


        ForexRate exchangeRateUSDKZT = exchangeRateClient.getExchangeRate("USD/KZT", apiKey);
        ForexRate exchangeRateUSDRUB = exchangeRateClient.getExchangeRate("USD/RUB", apiKey);


        exchangeRateRepository.save(forexRateMapper.forexRateToExchangeRate(exchangeRateUSDKZT));
        logger.info("Saved exchange rate for USD/KZT");

        exchangeRateRepository.save(forexRateMapper.forexRateToExchangeRate(exchangeRateUSDRUB));
        logger.info("Saved exchange rate for USD/RUB");
    }
}

