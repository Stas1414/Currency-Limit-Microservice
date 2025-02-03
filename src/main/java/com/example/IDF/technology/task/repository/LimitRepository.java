package com.example.IDF.technology.task.repository;

import com.example.IDF.technology.task.entity.AccountLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitRepository extends JpaRepository<AccountLimit, Long> {

    AccountLimit findByCategory(String category);

    @Query(value = "SELECT * FROM account_limit WHERE EXTRACT(MONTH FROM set_date) = :month AND category = :category ORDER BY set_date DESC LIMIT 1",
            nativeQuery = true)
    AccountLimit findLimitsByMonthAndCategory(@Param("month") int month, @Param("category") String category);

}
