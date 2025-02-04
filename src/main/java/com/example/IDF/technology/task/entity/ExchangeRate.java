package com.example.IDF.technology.task.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity class representing an exchange rate for a given currency pair.
 */
@Entity
public class ExchangeRate {

    /**
     * Unique identifier for the exchange rate entry.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Currency pair symbol (e.g., "USD/EUR").
     */
    @Column(nullable = false)
    private String symbol;

    /**
     * Date of the exchange rate entry.
     */
    @Column(nullable = false)
    private LocalDate date;

    /**
     * Closing exchange rate for the given date.
     */
    @Column(nullable = false)
    private BigDecimal closingRate;

    /**
     * Closing exchange rate from the previous trading day.
     */
    @Column(nullable = false)
    private BigDecimal previousClosingRate;

    /**
     * Default constructor.
     */
    public ExchangeRate() {
    }

    /**
     * Constructs an ExchangeRate instance with specified values.
     *
     * @param symbol              the currency pair symbol
     * @param date                the date of the exchange rate
     * @param closingRate         the closing rate for the given date
     * @param previousClosingRate the closing rate from the previous day
     */
    public ExchangeRate(String symbol, LocalDate date, BigDecimal closingRate, BigDecimal previousClosingRate) {
        this.symbol = symbol;
        this.date = date;
        this.closingRate = closingRate;
        this.previousClosingRate = previousClosingRate;
    }

    /**
     * Gets the ID of the exchange rate entry.
     *
     * @return the exchange rate ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the exchange rate entry.
     *
     * @param id the exchange rate ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the currency pair symbol.
     *
     * @return the currency pair symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets the currency pair symbol.
     *
     * @param symbol the currency pair symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets the date of the exchange rate entry.
     *
     * @return the date of the exchange rate entry
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the exchange rate entry.
     *
     * @param date the date of the exchange rate entry
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the closing exchange rate for the given date.
     *
     * @return the closing exchange rate
     */
    public BigDecimal getClosingRate() {
        return closingRate;
    }

    /**
     * Sets the closing exchange rate for the given date.
     *
     * @param closingRate the closing exchange rate
     */
    public void setClosingRate(BigDecimal closingRate) {
        this.closingRate = closingRate;
    }

    /**
     * Gets the closing exchange rate from the previous trading day.
     *
     * @return the previous day's closing exchange rate
     */
    public BigDecimal getPreviousClosingRate() {
        return previousClosingRate;
    }

    /**
     * Sets the closing exchange rate from the previous trading day.
     *
     * @param previousClosingRate the previous day's closing exchange rate
     */
    public void setPreviousClosingRate(BigDecimal previousClosingRate) {
        this.previousClosingRate = previousClosingRate;
    }
}
