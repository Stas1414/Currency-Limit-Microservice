package com.example.IDF.technology.task.service;

import java.util.logging.Logger;

import com.example.IDF.technology.task.entity.ExchangeRate;
import com.example.IDF.technology.task.feign.ExchangeRateClient;
import com.example.IDF.technology.task.repository.ExchangeRateRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
public class ForexService {

    private static final Logger logger = Logger.getLogger(ForexService.class.getName());

    @Value("${api.twelve.data.key}")
    private String apiKey;

    private final ExchangeRateClient exchangeRateClient;

    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public ForexService(ExchangeRateClient exchangeRateClient, ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateClient = exchangeRateClient;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @PostConstruct
    @Scheduled(cron = "0 30 11 * * ?")
    public void updateTask() {
        logger.info("Updating exchange rates...");
        Map<String, Object> exchangeRateUSDKZT = exchangeRateClient.getExchangeRate("USD/KZT", apiKey);
        Map<String, Object> exchangeRateUSDRUB = exchangeRateClient.getExchangeRate("USD/RUB", apiKey);

        exchangeRateRepository.save(getExchangeRate(exchangeRateUSDKZT));
        logger.info("Saved exchange rate for USD/KZT");
        exchangeRateRepository.save(getExchangeRate(exchangeRateUSDRUB));
        logger.info("Saved exchange rate for USD/RUB");
    }

    public static ExchangeRate getExchangeRate(Map<String, Object> exchangeRate) {
        ExchangeRate result = new ExchangeRate();
        result.setSymbol(exchangeRate.get("symbol").toString());
        result.setClosingRate(BigDecimal.valueOf(Double.parseDouble(exchangeRate.get("close").toString())));
        result.setPreviousClosingRate(BigDecimal.valueOf(Double.parseDouble(exchangeRate.get("previous_close").toString())));
        result.setDate(LocalDate.now());
        return result;
    }


}
