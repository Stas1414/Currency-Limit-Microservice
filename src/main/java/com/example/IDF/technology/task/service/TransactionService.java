package com.example.IDF.technology.task.service;

import com.example.IDF.technology.task.dto.ExceededTransactionDto;
import com.example.IDF.technology.task.entity.ExchangeRate;
import com.example.IDF.technology.task.entity.AccountLimit;
import com.example.IDF.technology.task.entity.Transaction;
import com.example.IDF.technology.task.feign.ExchangeRateClient;
import com.example.IDF.technology.task.repository.ExchangeRateRepository;
import com.example.IDF.technology.task.repository.LimitRepository;
import com.example.IDF.technology.task.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class TransactionService {

    private static final Logger logger = Logger.getLogger(TransactionService.class.getName());

    @Value("${api.twelve.data.key}")
    private String apiKey;

    private final TransactionRepository transactionRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateClient exchangeRateClient;
    private final LimitRepository limitRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, ExchangeRateRepository exchangeRateRepository, ExchangeRateClient exchangeRateClient, LimitRepository limitRepository) {
        this.transactionRepository = transactionRepository;
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateClient = exchangeRateClient;
        this.limitRepository = limitRepository;
    }

    public List<ExceededTransactionDto> getTransactionLimitExceededReported() {
        return transactionRepository.findTransactionByLimitExceeded();
    }

    @Transactional
    public Transaction getTransaction(Transaction transaction) {
        logger.info("Processing transaction: " + transaction);
        ExchangeRate exchangeRate = checkExchangeRate(transaction);

        AccountLimit limit = limitRepository.findLimitsByMonthAndCategory(LocalDateTime.now().getMonthValue(), transaction.getCategory());
        List<Transaction> transactions = transactionRepository.findTransactionsByMonthAndCategory(LocalDateTime.now().getMonthValue(), transaction.getCategory());
        transactions.add(transaction);
        if (exchangeRate == null) {
            logger.info("Exchange rate not found, fetching from API.");
            Map<String, Object> rate = exchangeRateClient.getExchangeRate("USD/" + transaction.getCurrency(), apiKey);
            ExchangeRate newExchangeRate = ForexService.getExchangeRate(rate);

            exchangeRateRepository.save(newExchangeRate);
            logger.info("New exchange rate saved: " + newExchangeRate);

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
        transactionRepository.save(transaction);
        logger.info("Transaction saved: " + transaction);
        return transaction;
    }

    private ExchangeRate checkExchangeRate(Transaction transaction) {
        logger.info("Checking exchange rate for transaction currency: " + transaction.getCurrency());
        String currency = transaction.getCurrency();
        return exchangeRateRepository.findByCurrency(currency);
    }

    private BigDecimal convertToUSD(BigDecimal amount, ExchangeRate exchangeRate, Transaction transaction) {
        DayOfWeek dayOfWeek = transaction.getDate().getDayOfWeek();
        BigDecimal rate = getRateForDayOfWeek(exchangeRate, dayOfWeek);
        return amount.divide(rate, 2, RoundingMode.HALF_UP);
    }

    private BigDecimal getRateForDayOfWeek(ExchangeRate exchangeRate, DayOfWeek dayOfWeek) {
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return exchangeRate.getPreviousClosingRate();
        } else {
            return exchangeRate.getClosingRate();
        }
    }
}
