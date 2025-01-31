package com.example.IDF.technology.task.repository;

import com.example.IDF.technology.task.entity.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {

    Limit findByCategory(String category);

    @Query(value = "SELECT * FROM limit WHERE EXTRACT(MONTH FROM set_date) = :month AND category = :category ORDER BY set_date DESC LIMIT 1",
            nativeQuery = true)
    Limit findLimitsByMonthAndCategory(@Param("month") int month, @Param("category") String category);

    /*
    @Query(value = "SELECT * FROM limit WHERE MONTH(set_date) = :month " +
               "AND category = :category " +
               "AND account_to_id = :accountToId " +
               "ORDER BY set_date DESC LIMIT 1",
       nativeQuery = true)
Limit findLimitsByMonthCategoryAndAccount(@Param("month") int month,
                                          @Param("category") String category,
                                          @Param("accountToId") Long accountToId);
     */
}
