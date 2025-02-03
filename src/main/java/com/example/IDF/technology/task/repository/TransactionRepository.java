package com.example.IDF.technology.task.repository;

import com.example.IDF.technology.task.dto.ExceededTransactionDto;
import com.example.IDF.technology.task.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    @Query(value = "SELECT * FROM transaction WHERE EXTRACT(MONTH FROM date) = :month AND category = :category ORDER BY date DESC",
            nativeQuery = true)
    List<Transaction> findTransactionsByMonthAndCategory(@Param("month") int month, @Param("category") String category);


    @Query(value = """
            SELECT
        t.account_from, t.account_to, t.currency AS currency_shortname,
        t.amount AS sum, t.category AS expense_category, t.date AS datetime,
        al.limit_amount AS limit_sum, al.set_date AS limit_datetime, al.currency AS limit_currency_shortname
    FROM transaction t
    JOIN account_limit al ON t.category = al.category
    WHERE t.limit_exceeded = true
""", nativeQuery = true)
    List<ExceededTransactionDto> findTransactionByLimitExceeded();
}
