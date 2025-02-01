package com.example.IDF.technology.task.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    private LocalDate date;

    private BigDecimal closingRate;

    private BigDecimal previousClosingRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getClosingRate() {
        return closingRate;
    }

    public void setClosingRate(BigDecimal closingRate) {
        this.closingRate = closingRate;
    }

    public BigDecimal getPreviousClosingRate() {
        return previousClosingRate;
    }

    public void setPreviousClosingRate(BigDecimal previousClosingRate) {
        this.previousClosingRate = previousClosingRate;
    }

    public ExchangeRate(String symbol, LocalDate date, BigDecimal closingRate, BigDecimal previousClosingRate) {
        this.symbol = symbol;
        this.date = date;
        this.closingRate = closingRate;
        this.previousClosingRate = previousClosingRate;
    }

    public ExchangeRate() {
    }
}
