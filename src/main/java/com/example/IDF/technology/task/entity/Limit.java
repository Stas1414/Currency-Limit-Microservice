package com.example.IDF.technology.task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private BigDecimal limit_amount;

    private String currency = "USD";

    private LocalDateTime set_date;

}

/*
id: Long или UUID — уникальный идентификатор лимита.
category: String — категория расхода (например, "товары" или "услуги").
limit_amount: BigDecimal — сумма лимита в долларах США.
currency: String — валюта лимита (в данном случае всегда "USD").
set_date: LocalDateTime — дата установки лимита.
 */
