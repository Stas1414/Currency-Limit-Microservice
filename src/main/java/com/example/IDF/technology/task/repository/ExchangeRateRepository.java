package com.example.IDF.technology.task.repository;

import com.example.IDF.technology.task.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for working with the {@link ExchangeRate} entity.
 * <p>
 * This interface provides methods for performing operations on the {@code exchange_rate} table in the database.
 * It uses Spring Data JPA for automatic query generation and CRUD operations support.
 * </p>
 */
@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    /**
     * Finds an {@link ExchangeRate} object by its currency symbol.
     * <p>
     * This method executes a query to retrieve the exchange rate data corresponding to the given symbol.
     * For example, the symbol "USD/EUR" will return the exchange rate between the US Dollar and the Euro.
     * </p>
     *
     * @param symbol The currency symbol, e.g., "USD/EUR".
     * @return An {@link ExchangeRate} object with the exchange rate for the given symbol,
     *         or {@code null} if no exchange rate is found.
     */
    ExchangeRate findBySymbol(String symbol);

    /**
     * Finds an {@link ExchangeRate} object by the currency.
     * <p>
     * This method executes a query to retrieve the exchange rate data using the currency,
     * for example, for the currency "EUR" it will find the exchange rate for USD/EUR.
     * </p>
     *
     * @param currency The currency code, e.g., "EUR".
     * @return An {@link ExchangeRate} object with the exchange rate for the given currency,
     *         or {@code null} if no exchange rate is found.
     */
    @Query(value = "SELECT * FROM exchange_rate e WHERE e.symbol = CONCAT('USD/', :currency)", nativeQuery = true)
    ExchangeRate findByCurrency(@Param("currency") String currency);
}
