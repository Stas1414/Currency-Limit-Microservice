package com.example.IDF.technology.task.dto;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) for representing account spending limits in different categories.
 */
public class AccountLimitDto {

    /**
     * The unique identifier of the account.
     */
    private Long accountId;

    /**
     * The category for which the spending limit is set.
     */
    private String category;

    /**
     * The maximum allowed spending amount for the specified category.
     */
    private BigDecimal limitAmount;

    /**
     * Constructs an AccountLimitDto with a specified limit amount and category.
     *
     * @param limitAmount the spending limit amount
     * @param category    the spending category
     */
    public AccountLimitDto(BigDecimal limitAmount, String category) {
        this.limitAmount = limitAmount;
        this.category = category;
    }

    /**
     * Constructs an AccountLimitDto with a specified category.
     * The limit amount is not set.
     *
     * @param category the spending category
     */
    public AccountLimitDto(String category) {
        this.category = category;
    }

    /**
     * Default constructor.
     */
    public AccountLimitDto() {
    }

    /**
     * Gets the account ID.
     *
     * @return the account ID
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * Sets the account ID.
     *
     * @param accountId the account ID to set
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets the spending category.
     *
     * @return the category name
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the spending category.
     *
     * @param category the category name to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the spending limit amount.
     *
     * @return the limit amount
     */
    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    /**
     * Sets the spending limit amount.
     *
     * @param limitAmount the limit amount to set
     */
    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }
}
