package com.example.IDF.technology.task.repository;

import com.example.IDF.technology.task.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    ExchangeRate findBySymbol(String symbol);

    @Query(value = "SELECT * FROM exchange_rate e WHERE e.symbol = CONCAT('USD/', :currency)", nativeQuery = true)
    ExchangeRate findByCurrency(@Param("currency") String currency);
}
