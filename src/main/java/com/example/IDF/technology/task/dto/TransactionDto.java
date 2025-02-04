package com.example.IDF.technology.task.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing a financial transaction.
 */
public class TransactionDto {

    /**
     * The ID of the sender's account.
     */
    private Long accountFrom;

    /**
     * The ID of the recipient's account.
     */
    private Long accountTo;

    /**
     * The date and time of the transaction.
     */
    private LocalDateTime date;

    /**
     * The amount of money transferred.
     */
    private BigDecimal amount;

    /**
     * The currency in which the transaction is made.
     */
    private String currency;

    /**
     * The category of the transaction (e.g., "Food", "Entertainment").
     */
    private String category;

    /**
     * Constructs a new TransactionDto with the specified parameters.
     *
     * @param accountFrom the sender's account ID
     * @param accountTo   the recipient's account ID
     * @param date        the date and time of the transaction
     * @param amount      the transaction amount
     * @param currency    the transaction currency
     * @param category    the transaction category
     */
    public TransactionDto(Long accountFrom, Long accountTo, LocalDateTime date,
                          BigDecimal amount, String currency, String category) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.category = category;
    }

    /**
     * Default constructor.
     */
    public TransactionDto() {
    }

    /**
     * Gets the sender's account ID.
     *
     * @return the sender's account ID
     */
    public Long getAccountFrom() {
        return accountFrom;
    }

    /**
     * Sets the sender's account ID.
     *
     * @param accountFrom the sender's account ID
     */
    public void setAccountFrom(Long accountFrom) {
        this.accountFrom = accountFrom;
    }

    /**
     * Gets the recipient's account ID.
     *
     * @return the recipient's account ID
     */
    public Long getAccountTo() {
        return accountTo;
    }

    /**
     * Sets the recipient's account ID.
     *
     * @param accountTo the recipient's account ID
     */
    public void setAccountTo(Long accountTo) {
        this.accountTo = accountTo;
    }

    /**
     * Gets the date and time of the transaction.
     *
     * @return the transaction date and time
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the date and time of the transaction.
     *
     * @param date the transaction date and time
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Gets the transaction amount.
     *
     * @return the transaction amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the transaction amount.
     *
     * @param amount the transaction amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the transaction currency.
     *
     * @return the transaction currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the transaction currency.
     *
     * @param currency the transaction currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Gets the transaction category.
     *
     * @return the transaction category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the transaction category.
     *
     * @param category the transaction category
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
