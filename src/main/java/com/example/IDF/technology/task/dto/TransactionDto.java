package com.example.IDF.technology.task.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDto {

    private Long accountFrom;

    private Long accountTo;

    private LocalDateTime date;

    private BigDecimal amount;

    private String currency;

    private String category;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public TransactionDto(Long accountFrom, Long accountTo, LocalDateTime date, BigDecimal amount, String currency, String category) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.category = category;
    }

    public TransactionDto() {
    }
}
