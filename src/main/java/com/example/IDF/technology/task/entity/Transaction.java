package com.example.IDF.technology.task.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity class representing a financial transaction.
 */
@Entity
public class Transaction {

    /**
     * Unique identifier for the transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Account ID from which the funds are withdrawn.
     */
    @Column(nullable = false)
    private Long accountFrom;

    /**
     * Account ID to which the funds are deposited.
     */
    @Column(nullable = false)
    private Long accountTo;

    /**
     * Date and time of the transaction.
     */
    @Column(nullable = false)
    private LocalDateTime date;

    /**
     * Amount of money involved in the transaction.
     */
    @Column(nullable = false)
    private BigDecimal amount;

    /**
     * Currency of the transaction (e.g., "USD", "EUR").
     */
    @Column(nullable = false)
    private String currency;

    /**
     * Category of the transaction (e.g., "Groceries", "Rent").
     */
    @Column(nullable = false)
    private String category;

    /**
     * Flag indicating whether the transaction exceeds a predefined account limit.
     */
    @Column(nullable = false)
    private boolean limitExceeded;

    /**
     * Default constructor.
     */
    public Transaction() {
    }

    /**
     * Constructs a Transaction instance with specified values.
     *
     * @param accountFrom  the sender account ID
     * @param accountTo    the recipient account ID
     * @param date         the date and time of the transaction
     * @param amount       the transaction amount
     * @param currency     the currency of the transaction
     * @param category     the category of the transaction
     */
    public Transaction(Long accountFrom, Long accountTo, LocalDateTime date, BigDecimal amount, String currency, String category) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.category = category;
    }

    /**
     * Gets the transaction ID.
     *
     * @return the transaction ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the transaction ID.
     *
     * @param id the transaction ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the sender account ID.
     *
     * @return the sender account ID
     */
    public Long getAccountFrom() {
        return accountFrom;
    }

    /**
     * Sets the sender account ID.
     *
     * @param accountFrom the sender account ID
     */
    public void setAccountFrom(Long accountFrom) {
        this.accountFrom = accountFrom;
    }

    /**
     * Gets the recipient account ID.
     *
     * @return the recipient account ID
     */
    public Long getAccountTo() {
        return accountTo;
    }

    /**
     * Sets the recipient account ID.
     *
     * @param accountTo the recipient account ID
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

    /**
     * Checks if the transaction exceeds a predefined account limit.
     *
     * @return true if the limit is exceeded, false otherwise
     */
    public boolean isLimitExceeded() {
        return limitExceeded;
    }

    /**
     * Sets the flag indicating whether the transaction exceeds a predefined account limit.
     *
     * @param limitExceeded true if the limit is exceeded, false otherwise
     */
    public void setLimitExceeded(boolean limitExceeded) {
        this.limitExceeded = limitExceeded;
    }
}
