package com.example.IDF.technology.task.repository;

import com.example.IDF.technology.task.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

//    @NativeQuery("")
//    List<Transaction> findTransactionByLimitExceeded();

    @Query(value = "SELECT * FROM transaction WHERE EXTRACT(MONTH FROM date) = :month ORDER BY date DESC",
            nativeQuery = true)
    List<Transaction> findTransactionsByMonth(@Param("month") int month);
}
