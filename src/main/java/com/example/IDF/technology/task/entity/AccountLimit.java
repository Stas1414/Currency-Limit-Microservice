package com.example.IDF.technology.task.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity class representing an account spending limit.
 */
@Entity
public class AccountLimit {

    /**
     * Unique identifier for the account limit.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Category of the spending limit (e.g., "Food", "Entertainment").
     */
    @Column(nullable = false)
    private String category;

    /**
     * The maximum spending limit amount.
     * Default value is 1000.
     */
    @Column(nullable = false)
    private BigDecimal limitAmount = BigDecimal.valueOf(1000);

    /**
     * The currency in which the limit is set.
     * Default value is "USD".
     */
    @Column(nullable = false)
    private String currency = "USD";

    /**
     * The date when the limit was set.
     */
    @Column(nullable = false)
    private LocalDateTime setDate;

    /**
     * Default constructor.
     */
    public AccountLimit() {
    }

    /**
     * Constructs an AccountLimit with specified category, limit amount, and set date.
     *
     * @param category    the category of the spending limit
     * @param limitAmount the maximum spending limit amount
     * @param setDate     the date when the limit was set
     */
    public AccountLimit(String category, BigDecimal limitAmount, LocalDateTime setDate) {
        this.category = category;
        this.limitAmount = limitAmount;
        this.setDate = setDate;
    }

    /**
     * Constructs an AccountLimit with specified category and set date.
     * Uses the default limit amount.
     *
     * @param category the category of the spending limit
     * @param setDate  the date when the limit was set
     */
    public AccountLimit(String category, LocalDateTime setDate) {
        this.category = category;
        this.setDate = setDate;
    }

    /**
     * Gets the ID of the account limit.
     *
     * @return the account limit ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the account limit.
     *
     * @param id the account limit ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the category of the spending limit.
     *
     * @return the spending limit category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the spending limit.
     *
     * @param category the spending limit category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the maximum spending limit amount.
     *
     * @return the spending limit amount
     */
    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    /**
     * Sets the maximum spending limit amount.
     *
     * @param limitAmount the spending limit amount
     */
    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    /**
     * Gets the currency of the spending limit.
     *
     * @return the currency of the spending limit
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the currency of the spending limit.
     *
     * @param currency the currency of the spending limit
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Gets the date when the limit was set.
     *
     * @return the date when the limit was set
     */
    public LocalDateTime getSetDate() {
        return setDate;
    }

    /**
     * Sets the date when the limit was set.
     *
     * @param setDate the date when the limit was set
     */
    public void setSetDate(LocalDateTime setDate) {
        this.setDate = setDate;
    }
}
