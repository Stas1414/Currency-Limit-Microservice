package com.example.IDF.technology.task.dto;

import java.math.BigDecimal;

public class AccountLimitDto {

    private Long accountId;

    private String category;

    private BigDecimal limitAmount;

    public AccountLimitDto(Long accountId, BigDecimal limitAmount, String category) {
        this.accountId = accountId;
        this.limitAmount = limitAmount;
        this.category = category;
    }

    public AccountLimitDto(Long accountId, String category) {
        this.accountId = accountId;
        this.category = category;
    }

    public AccountLimitDto() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }
}
