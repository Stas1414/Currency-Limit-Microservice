package com.example.IDF.technology.task.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ExceededTransactionDto {
    private Long accountFrom;
    private Long accountTo;
    private String currencyShortname;
    private BigDecimal sum;
    private String expenseCategory;
    private LocalDateTime datetime;
    private BigDecimal limitSum;
    private LocalDateTime limitDatetime;
    private String limitCurrencyShortname;

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

    public Long getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Long accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Long getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Long accountTo) {
        this.accountTo = accountTo;
    }

    public String getCurrencyShortname() {
        return currencyShortname;
    }

    public void setCurrencyShortname(String currencyShortname) {
        this.currencyShortname = currencyShortname;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public BigDecimal getLimitSum() {
        return limitSum;
    }

    public void setLimitSum(BigDecimal limitSum) {
        this.limitSum = limitSum;
    }

    public LocalDateTime getLimitDatetime() {
        return limitDatetime;
    }

    public void setLimitDatetime(LocalDateTime limitDatetime) {
        this.limitDatetime = limitDatetime;
    }

    public String getLimitCurrencyShortname() {
        return limitCurrencyShortname;
    }

    public void setLimitCurrencyShortname(String limitCurrencyShortname) {
        this.limitCurrencyShortname = limitCurrencyShortname;
    }
}

