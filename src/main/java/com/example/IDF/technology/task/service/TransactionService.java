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
import java.util.Map;
import java.util.logging.Logger;

/**
 * Service class for processing transactions and managing account limits.
 * <p>
 * This service handles transaction processing, checks exchange rates,
 * and ensures that transactions do not exceed account limits. It interacts with
 * various repositories to fetch and save data related to transactions, exchange rates,
 * and account limits.
 * </p>
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
     * Constructs the {@link TransactionService} with the necessary repositories and services.
     *
     * @param transactionRepository Repository for transaction data.
     * @param exchangeRateRepository Repository for exchange rate data.
     * @param exchangeRateClient Client for fetching exchange rate data from an external API.
     * @param limitRepository Repository for account limits data.
     * @param forexRateMapper Mapper for converting Forex rates.
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
     * Retrieves all transactions where the limit has been exceeded.
     *
     * @return A list of {@link ExceededTransactionDto} containing transactions
     *         that have exceeded their account limits.
     */
    public List<ExceededTransactionDto> getTransactionLimitExceededReported() {
        return transactionRepository.findTransactionByLimitExceeded();
    }

    /**
     * Processes a transaction by checking the corresponding exchange rate,
     * ensuring that the transaction does not exceed the account limit,
     * and saving the transaction.
     * <p>
     * If the exchange rate for the transaction's currency is not found,
     * it is fetched from an external API. The total sum of transactions for
     * the current month is calculated and compared to the account limit.
     * If the limit is exceeded, the {@code limitExceeded} flag is set to {@code true}.
     * </p>
     *
     * @param transaction The transaction to be processed.
     * @return The processed {@link Transaction} object with updated data.
     */
    @Transactional
    public Transaction getTransaction(Transaction transaction) {
        logger.info("Processing transaction: " + transaction);

        // Check for existing exchange rate
        ExchangeRate exchangeRate = checkExchangeRate(transaction);

        // Get account limit for the current month and category
        AccountLimit limit = limitRepository.findLimitsByMonthAndCategory(
                LocalDateTime.now().getMonthValue(), transaction.getCategory());
        List<Transaction> transactions = transactionRepository.findTransactionsByMonthAndCategory(
                LocalDateTime.now().getMonthValue(), transaction.getCategory());

        transactions.add(transaction);

        // If exchange rate is not found, fetch it from the external API
        if (exchangeRate == null) {
            logger.info("Exchange rate not found, fetching from API.");
            ForexRate rate = exchangeRateClient.getExchangeRate("USD/" + transaction.getCurrency(), apiKey);
            ExchangeRate newExchangeRate = forexRateMapper.ForexRateToExchangeRate(rate);
            exchangeRateRepository.save(newExchangeRate);
            logger.info("New exchange rate saved: " + newExchangeRate);

            // Calculate the total sum in USD and check if the limit is exceeded
            BigDecimal totalSumInUSD = getSumOfTransactionForMonth(transactions, newExchangeRate);
            if (totalSumInUSD.compareTo(limit.getLimitAmount()) > 0) {
                transaction.setLimitExceeded(true);
            }
        } else {
            // Calculate the total sum in USD using the existing exchange rate
            BigDecimal totalSumInUSD = getSumOfTransactionForMonth(transactions, exchangeRate);
            if (totalSumInUSD.compareTo(limit.getLimitAmount()) > 0) {
                transaction.setLimitExceeded(true);
            }
        }

        // Save the processed transaction
        transactionRepository.save(transaction);
        logger.info("Transaction saved: " + transaction);
        return transaction;
    }

    /**
     * Calculates the total sum of transactions for the current month in USD,
     * using the provided exchange rate for conversion.
     *
     * @param transactions List of transactions for the current month.
     * @param exchangeRate The exchange rate to convert transaction amounts to USD.
     * @return The total sum of transactions in USD.
     */
    private BigDecimal getSumOfTransactionForMonth(List<Transaction> transactions, ExchangeRate exchangeRate) {
        return transactions.stream()
                .map(transaction1 -> convertToUSD(transaction1.getAmount(), exchangeRate, transaction1))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Checks if an exchange rate exists for the currency of the transaction.
     *
     * @param transaction The transaction to check.
     * @return The {@link ExchangeRate} for the transaction's currency, or {@code null} if not found.
     */
    private ExchangeRate checkExchangeRate(Transaction transaction) {
        logger.info("Checking exchange rate for transaction currency: " + transaction.getCurrency());
        String currency = transaction.getCurrency();
        return exchangeRateRepository.findByCurrency(currency);
    }

    /**
     * Converts the transaction amount to USD using the exchange rate and the transaction's date.
     *
     * @param amount The amount to convert.
     * @param exchangeRate The exchange rate to use for the conversion.
     * @param transaction The transaction that contains the date for determining the rate.
     * @return The amount in USD.
     */
    private BigDecimal convertToUSD(BigDecimal amount, ExchangeRate exchangeRate, Transaction transaction) {
        DayOfWeek dayOfWeek = transaction.getDate().getDayOfWeek();
        BigDecimal rate = getRateForDayOfWeek(exchangeRate, dayOfWeek);
        return amount.divide(rate, 2, RoundingMode.HALF_UP);
    }

    /**
     * Retrieves the appropriate exchange rate based on the day of the week.
     * <p>
     * If the transaction occurs on a Saturday or Sunday, the previous closing rate is used.
     * Otherwise, the current closing rate is used.
     * </p>
     *
     * @param exchangeRate The exchange rate to check.
     * @param dayOfWeek The day of the week for the transaction.
     * @return The exchange rate for the specified day.
     */
    private BigDecimal getRateForDayOfWeek(ExchangeRate exchangeRate, DayOfWeek dayOfWeek) {
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return exchangeRate.getPreviousClosingRate();
        } else {
            return exchangeRate.getClosingRate();
        }
    }
}
