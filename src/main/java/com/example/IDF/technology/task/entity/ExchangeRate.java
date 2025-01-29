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

    private String currency_pair;

    private LocalDate date;

    private BigDecimal closing_rate;

    private BigDecimal previous_closing_rate;
}

/*
id: Long или UUID — уникальный идентификатор курса валют.
currency_pair: String — валютная пара (например, "KZT/USD", "RUB/USD").
date: LocalDate — дата курса валют.
closing_rate: BigDecimal — курс закрытия на данный день.
previous_close: BigDecimal — курс закрытия за предыдущий день (если текущий курс недоступен).
 */
