package com.example.IDF.technology.task.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for representing transactions that exceed the set limits.
 */
public class ExceededTransactionDto {

    /**
     * The account ID from which the transaction originates.
     */
    private Long accountFrom;

    /**
     * The account ID to which the transaction is sent.
     */
    private Long accountTo;

    /**
     * The currency in which the transaction is made, represented as a short name (e.g., "USD").
     */
    private String currencyShortname;

    /**
     * The amount of money involved in the transaction.
     */
    private BigDecimal sum;

    /**
     * The category of expense related to the transaction.
     */
    private String expenseCategory;

    /**
     * The timestamp of when the transaction occurred.
     */
    private LocalDateTime datetime;

    /**
     * The limit amount that was exceeded.
     */
    private BigDecimal limitSum;

    /**
     * The timestamp when the spending limit was set.
     */
    private LocalDateTime limitDatetime;

    /**
     * The currency in which the spending limit is defined.
     */
    private String limitCurrencyShortname;

    /**
     * Constructs an ExceededTransactionDto with the specified details.
     *
     * @param accountFrom           the sender's account ID
     * @param accountTo             the recipient's account ID
     * @param currencyShortname     the transaction currency
     * @param sum                   the transaction amount
     * @param expenseCategory       the category of the expense
     * @param datetime              the timestamp of the transaction
     * @param limitSum              the exceeded limit amount
     * @param limitDatetime         the timestamp when the limit was set
     * @param limitCurrencyShortname the currency of the spending limit
     */
    public ExceededTransactionDto(Long accountFrom, Long accountTo, String currencyShortname,
                                  BigDecimal sum, String expenseCategory, Timestamp datetime,
                                  BigDecimal limitSum, Timestamp limitDatetime, String limitCurrencyShortname) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.currencyShortname = currencyShortname;
        this.sum = sum;
        this.expenseCategory = expenseCategory;
        this.datetime = datetime.toLocalDateTime();
        this.limitSum = limitSum;
        this.limitDatetime = limitDatetime.toLocalDateTime();
        this.limitCurrencyShortname = limitCurrencyShortname;
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
     * @param accountFrom the sender's account ID to set
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
     * @param accountTo the recipient's account ID to set
     */
    public void setAccountTo(Long accountTo) {
        this.accountTo = accountTo;
    }

    /**
     * Gets the currency short name.
     *
     * @return the currency short name
     */
    public String getCurrencyShortname() {
        return currencyShortname;
    }

    /**
     * Sets the currency short name.
     *
     * @param currencyShortname the currency short name to set
     */
    public void setCurrencyShortname(String currencyShortname) {
        this.currencyShortname = currencyShortname;
    }

    /**
     * Gets the transaction amount.
     *
     * @return the transaction amount
     */
    public BigDecimal getSum() {
        return sum;
    }

    /**
     * Sets the transaction amount.
     *
     * @param sum the transaction amount to set
     */
    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    /**
     * Gets the expense category.
     *
     * @return the expense category
     */
    public String getExpenseCategory() {
        return expenseCategory;
    }

    /**
     * Sets the expense category.
     *
     * @param expenseCategory the expense category to set
     */
    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    /**
     * Gets the transaction timestamp.
     *
     * @return the transaction timestamp
     */
    public LocalDateTime getDatetime() {
        return datetime;
    }

    /**
     * Sets the transaction timestamp.
     *
     * @param datetime the transaction timestamp to set
     */
    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    /**
     * Gets the exceeded limit amount.
     *
     * @return the exceeded limit amount
     */
    public BigDecimal getLimitSum() {
        return limitSum;
    }

    /**
     * Sets the exceeded limit amount.
     *
     * @param limitSum the exceeded limit amount to set
     */
    public void setLimitSum(BigDecimal limitSum) {
        this.limitSum = limitSum;
    }

    /**
     * Gets the timestamp when the spending limit was set.
     *
     * @return the spending limit timestamp
     */
    public LocalDateTime getLimitDatetime() {
        return limitDatetime;
    }

    /**
     * Sets the timestamp when the spending limit was set.
     *
     * @param limitDatetime the spending limit timestamp to set
     */
    public void setLimitDatetime(LocalDateTime limitDatetime) {
        this.limitDatetime = limitDatetime;
    }

    /**
     * Gets the currency of the spending limit.
     *
     * @return the spending limit currency
     */
    public String getLimitCurrencyShortname() {
        return limitCurrencyShortname;
    }

    /**
     * Sets the currency of the spending limit.
     *
     * @param limitCurrencyShortname the spending limit currency to set
     */
    public void setLimitCurrencyShortname(String limitCurrencyShortname) {
        this.limitCurrencyShortname = limitCurrencyShortname;
    }
}
