package com.example.IDF.technology.task.entity;


import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private BigDecimal closingRate;

    @Column(nullable = false)
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
