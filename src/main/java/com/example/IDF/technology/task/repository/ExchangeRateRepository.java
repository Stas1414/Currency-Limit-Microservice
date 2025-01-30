package com.example.IDF.technology.task.repository;

import com.example.IDF.technology.task.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    ExchangeRate findBySymbol(String symbol);
}
