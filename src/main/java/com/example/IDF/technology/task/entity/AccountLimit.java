package com.example.IDF.technology.task.entity;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class AccountLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
//    private Long accountToId;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private BigDecimal limitAmount = BigDecimal.valueOf(1000);

    @Column(nullable = false)
    private String currency = "USD";

    @Column(nullable = false)
    private LocalDateTime setDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getAccountToId() {
//        return accountToId;
//    }
//
//    public void setAccountToId(Long accountToId) {
//        this.accountToId = accountToId;
//    }

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getSetDate() {
        return setDate;
    }

    public void setSetDate(LocalDateTime setDate) {
        this.setDate = setDate;
    }

    public AccountLimit() {
    }

    public AccountLimit(String category, BigDecimal limitAmount, LocalDateTime setDate) {
        this.category = category;
        this.limitAmount = limitAmount;
        this.setDate = setDate;
    }

    public AccountLimit(String category, LocalDateTime setDate) {
        this.category = category;
        this.setDate = setDate;
    }
}

