package com.example.IDF.technology.task.service;

import com.example.IDF.technology.task.entity.ExchangeRate;
import com.example.IDF.technology.task.entity.Limit;
import com.example.IDF.technology.task.entity.Transaction;
import com.example.IDF.technology.task.feign.ExchangeRateClient;
import com.example.IDF.technology.task.repository.ExchangeRateRepository;
import com.example.IDF.technology.task.repository.LimitRepository;
import com.example.IDF.technology.task.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    @Value("${api.twelvedata.key}")
    private String apiKey;

    private final TransactionRepository transactionRepository;

    private final ExchangeRateRepository exchangeRateRepository;

    private final ExchangeRateClient exchangeRateClient;

    private final LimitRepository limitRepository;

    public Transaction getTransaction(Transaction transaction) {
        ExchangeRate exchangeRate = checkExchangeRate(transaction);

        Limit limit = limitRepository.findLimitsByMonthAndCategory(LocalDateTime.now().getMonthValue(), transaction.getCategory());
        List<Transaction> transactions = transactionRepository.findTransactionsByMonth(LocalDateTime.now().getMonthValue());

        if (exchangeRate == null) {
            Map<String, Object> rate = exchangeRateClient.getExchangeRate(transaction.getCurrency(), apiKey);
            ExchangeRate newExchangeRate = ForexService.getExchangeRate(rate);

            exchangeRateRepository.save(newExchangeRate);

            BigDecimal totalSumInUSD = transactions.stream()
                    .map(transaction1 -> convertToUSD(transaction1.getAmount(), newExchangeRate, transaction1))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            if (totalSumInUSD.compareTo(limit.getLimitAmount()) > 0) {
                transaction.setLimitExceeded(true);
            }
        } else {
            BigDecimal totalSumInUSD = transactions.stream()
                    .map(transaction1 -> convertToUSD(transaction1.getAmount(), exchangeRate, transaction1))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            if (totalSumInUSD.compareTo(limit.getLimitAmount()) > 0) {
                transaction.setLimitExceeded(true);
            }
        }
        return transaction;
    }

    private ExchangeRate checkExchangeRate(Transaction transaction) {
        String currency = transaction.getCurrency();
        String regex = String.format("USD/%s|%s/USD", currency, currency);
        List<ExchangeRate> exchangeRates = exchangeRateRepository.findAll();
        Optional<ExchangeRate> exchangeRateOpt = exchangeRates.stream()
                .filter(rate -> rate.getSymbol().matches(regex))
                .findFirst();
        return exchangeRateOpt.orElse(null);

    }

    public BigDecimal convertToUSD(BigDecimal amount, ExchangeRate exchangeRate, Transaction transaction) {
        DayOfWeek dayOfWeek = transaction.getDate().getDayOfWeek();
        BigDecimal rate = getRateForDayOfWeek(exchangeRate, dayOfWeek);

        String currencyPair = exchangeRate.getSymbol();
        if (currencyPair.startsWith("USD")) {
            return amount.multiply(rate);
        } else if (currencyPair.endsWith("USD")) {
            return amount.divide(rate, 2, RoundingMode.HALF_UP);
        } else {
            throw new IllegalArgumentException("Неподдерживаемый формат валютной пары: " + currencyPair);
        }
    }

    private BigDecimal getRateForDayOfWeek(ExchangeRate exchangeRate, DayOfWeek dayOfWeek) {
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return exchangeRate.getPreviousClosingRate();
        } else {
            return exchangeRate.getClosingRate();
        }
    }

}
