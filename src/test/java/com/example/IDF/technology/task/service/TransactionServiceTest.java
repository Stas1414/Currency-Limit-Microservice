package com.example.IDF.technology.task.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.IDF.technology.task.entity.AccountLimit;
import com.example.IDF.technology.task.entity.ExchangeRate;
import com.example.IDF.technology.task.entity.Transaction;
import com.example.IDF.technology.task.feign.ExchangeRateClient;
import com.example.IDF.technology.task.mapper.ForexRateMapper;
import com.example.IDF.technology.task.mapperImpl.ForexRateMapperImpl;
import com.example.IDF.technology.task.model.ForexRate;
import com.example.IDF.technology.task.repository.ExchangeRateRepository;
import com.example.IDF.technology.task.repository.LimitRepository;
import com.example.IDF.technology.task.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private LimitRepository limitRepository;

    @Mock
    private ExchangeRateClient exchangeRateClient;

    @Mock
    private ForexRateMapperImpl forexRateMapper;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction transaction;
    private ExchangeRate exchangeRate;
    private AccountLimit limit;
    private final List<Transaction> transactions = new ArrayList<>();

    @BeforeEach
    void setUp() {
        transaction = new Transaction();
        transaction.setCategory("service");
        transaction.setCurrency("EUR");
        transaction.setAmount(BigDecimal.valueOf(100));
        transaction.setDate(LocalDateTime.now());

        exchangeRate = new ExchangeRate();
        exchangeRate.setSymbol("USD/EUR");
        exchangeRate.setClosingRate(BigDecimal.valueOf(1.1));

        limit = new AccountLimit();
        limit.setLimitAmount(BigDecimal.valueOf(500));
        limit.setSetDate(LocalDateTime.now());
    }

    @Test
    void shouldProcessTransactionSuccessfully() {
        when(exchangeRateRepository.findByCurrency("EUR")).thenReturn(exchangeRate);
        when(limitRepository.findLimitsByMonthAndCategory(anyInt(), eq("service"))).thenReturn(limit);
        when(transactionRepository.findTransactionsByMonthAndCategory(anyInt(), eq("service"))).thenReturn(transactions);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.getTransaction(transaction);

        assertNotNull(result);
        assertFalse(result.isLimitExceeded());
        verify(transactionRepository).save(transaction);
    }

    @Test
    void shouldFetchExchangeRateIfNotFound() {
        when(exchangeRateRepository.findByCurrency("EUR")).thenReturn(null);
        when(exchangeRateClient.getExchangeRate(eq("USD/" + transaction.getCurrency()), isNull()))
                .thenReturn(new ForexRate("USD/EUR", BigDecimal.valueOf(1.2)));
        when(forexRateMapper.forexRateToExchangeRate(any(ForexRate.class))).thenReturn(exchangeRate);
        when(limitRepository.findLimitsByMonthAndCategory(anyInt(), eq("service"))).thenReturn(limit);
        when(transactionRepository.findTransactionsByMonthAndCategory(anyInt(), eq("service"))).thenReturn(transactions);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.getTransaction(transaction);

        assertNotNull(result);
        assertFalse(result.isLimitExceeded());
        verify(exchangeRateRepository).save(exchangeRate);
    }

    @Test
    void shouldMarkTransactionAsLimitExceeded() {
        List<Transaction> list = new ArrayList<>();
        list.add(new Transaction(BigDecimal.valueOf(450), LocalDateTime.now()));
        list.add(new Transaction(BigDecimal.valueOf(100), LocalDateTime.now()));
        when(exchangeRateRepository.findByCurrency("EUR")).thenReturn(exchangeRate);
        when(limitRepository.findLimitsByMonthAndCategory(anyInt(), eq("service"))).thenReturn(limit);
        when(transactionRepository.findTransactionsByMonthAndCategory(anyInt(), eq("service")))
                .thenReturn(list);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.getTransaction(transaction);

        assertNotNull(result);
        assertTrue(result.isLimitExceeded());
    }
}
