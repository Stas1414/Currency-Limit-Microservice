package com.example.IDF.technology.task.service;

import com.example.IDF.technology.task.dto.ExceededTransactionDto;
import com.example.IDF.technology.task.entity.ExchangeRate;
import com.example.IDF.technology.task.entity.AccountLimit;
import com.example.IDF.technology.task.entity.Transaction;
import com.example.IDF.technology.task.feign.ExchangeRateClient;
import com.example.IDF.technology.task.mapperImpl.ForexRateMapperImpl;
import com.example.IDF.technology.task.model.ForexRate;
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
import java.util.logging.Logger;

/**
 * Service class for handling transactions and managing account limits.
 *
 * <p>This service is responsible for processing financial transactions, checking exchange rates,
 * verifying account limits, and ensuring transactions comply with set limits. It interacts with
 * various repositories and external APIs to fetch and store transaction and exchange rate data.</p>
 */
@Service
public class TransactionService {

    private static final Logger logger = Logger.getLogger(TransactionService.class.getName());

    @Value("${api.twelve.data.key}")
    private String apiKey;

    private final TransactionRepository transactionRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateClient exchangeRateClient;
    private final LimitRepository limitRepository;
    private final ForexRateMapperImpl forexRateMapper;

    /**
     * Constructs a {@link TransactionService} instance with required dependencies.
     *
     * @param transactionRepository Repository handling transaction persistence.
     * @param exchangeRateRepository Repository handling exchange rate persistence.
     * @param exchangeRateClient Client for fetching live exchange rates from external API.
     * @param limitRepository Repository handling account limits.
     * @param forexRateMapper Mapper for converting Forex rate data to internal entity format.
     */
    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              ExchangeRateRepository exchangeRateRepository,
                              ExchangeRateClient exchangeRateClient,
                              LimitRepository limitRepository,
                              ForexRateMapperImpl forexRateMapper) {
        this.transactionRepository = transactionRepository;
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateClient = exchangeRateClient;
        this.limitRepository = limitRepository;
        this.forexRateMapper = forexRateMapper;
    }

    /**
     * Retrieves a list of transactions where the account limit has been exceeded.
     *
     * @return A list of {@link ExceededTransactionDto} representing exceeded transactions.
     */
    public List<ExceededTransactionDto> getTransactionLimitExceededReported() {
        return transactionRepository.findTransactionByLimitExceeded();
    }

    /**
     * Processes a transaction by checking exchange rates, validating limits, and saving the transaction.
     *
     * <p>If the exchange rate for the given currency is not found, it is fetched from an external API.
     * The total sum of transactions for the current month is computed and checked against the account limit.
     * If the limit is exceeded, the transaction is marked accordingly.</p>
     *
     * @param transaction The transaction to process.
     * @return The processed {@link Transaction} with updated status.
     */
    @Transactional
    public Transaction getTransaction(Transaction transaction) {
        logger.info("Processing transaction: " + transaction);

        ExchangeRate exchangeRate = checkExchangeRate(transaction);
        AccountLimit limit = limitRepository.findLimitsByMonthAndCategory(
                LocalDateTime.now().getMonthValue(), transaction.getCategory());
        List<Transaction> transactions = transactionRepository.findTransactionsByMonthAndCategory(
                LocalDateTime.now().getMonthValue(), transaction.getCategory());

        transactions.add(transaction);

        if (exchangeRate == null) {
            logger.info("Exchange rate not found, fetching from API.");
            ForexRate rate = exchangeRateClient.getExchangeRate("USD/" + transaction.getCurrency(), apiKey);
            ExchangeRate newExchangeRate = forexRateMapper.forexRateToExchangeRate(rate);
            exchangeRateRepository.save(newExchangeRate);
            logger.info("New exchange rate saved: " + newExchangeRate);

            BigDecimal totalSumInUSD = getSumOfTransactionForMonth(transactions, newExchangeRate);
            if (totalSumInUSD.compareTo(limit.getLimitAmount()) > 0) {
                transaction.setLimitExceeded(true);
            }
        } else {
            BigDecimal totalSumInUSD = getSumOfTransactionForMonth(transactions, exchangeRate);
            if (totalSumInUSD.compareTo(limit.getLimitAmount()) > 0) {
                transaction.setLimitExceeded(true);
            }
        }

        transactionRepository.save(transaction);
        logger.info("Transaction saved: " + transaction);
        return transaction;
    }

    /**
     * Calculates the total sum of transactions for the current month in USD.
     *
     * @param transactions List of transactions for the current month.
     * @param exchangeRate Exchange rate used for conversion.
     * @return Total transaction sum in USD.
     */
    private BigDecimal getSumOfTransactionForMonth(List<Transaction> transactions, ExchangeRate exchangeRate) {
        return transactions.stream()
                .map(transaction1 -> convertToUSD(transaction1.getAmount(), exchangeRate, transaction1))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Checks if an exchange rate exists for the given transaction currency.
     *
     * @param transaction The transaction to verify.
     * @return The {@link ExchangeRate} entity if found, otherwise {@code null}.
     */
    private ExchangeRate checkExchangeRate(Transaction transaction) {
        logger.info("Checking exchange rate for transaction currency: " + transaction.getCurrency());
        return exchangeRateRepository.findByCurrency(transaction.getCurrency());
    }

    /**
     * Converts a transaction amount to USD based on the exchange rate and transaction date.
     *
     * @param amount Amount to convert.
     * @param exchangeRate Exchange rate to apply.
     * @param transaction Transaction providing the date reference.
     * @return Converted amount in USD.
     */
    private BigDecimal convertToUSD(BigDecimal amount, ExchangeRate exchangeRate, Transaction transaction) {
        DayOfWeek dayOfWeek = transaction.getDate().getDayOfWeek();
        BigDecimal rate = getRateForDayOfWeek(exchangeRate, dayOfWeek);
        return amount.divide(rate, 2, RoundingMode.HALF_UP);
    }

    /**
     * Retrieves the appropriate exchange rate based on the transaction's day of the week.
     *
     * <p>If the transaction occurs on a weekend, the previous closing rate is used.
     * Otherwise, the current closing rate is applied.</p>
     *
     * @param exchangeRate The exchange rate entity.
     * @param dayOfWeek The day of the transaction.
     * @return Corresponding exchange rate.
     */
    private BigDecimal getRateForDayOfWeek(ExchangeRate exchangeRate, DayOfWeek dayOfWeek) {
        return (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)
                ? exchangeRate.getPreviousClosingRate()
                : exchangeRate.getClosingRate();
    }
}
