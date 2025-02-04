package com.example.IDF.technology.task.repository;

import com.example.IDF.technology.task.entity.AccountLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for working with the {@link AccountLimit} entity.
 * <p>
 * This interface provides methods to perform operations on the {@code account_limit} table in the database,
 * leveraging the capabilities of Spring Data JPA for working with data and executing queries.
 * </p>
 */
@Repository
public interface LimitRepository extends JpaRepository<AccountLimit, Long> {

    /**
     * Finds an {@link AccountLimit} object by its category.
     * <p>
     * This method executes a query to retrieve the account limit data based on the provided category.
     * For example, for the category "Credit", it will return the limit for this category.
     * </p>
     *
     * @param category The account limit category.
     * @return An {@link AccountLimit} object for the specified category,
     *         or {@code null} if no limit is found for this category.
     */
    AccountLimit findByCategory(String category);

    /**
     * Finds the most recent {@link AccountLimit} object by month and category.
     * <p>
     * This method executes a native SQL query to retrieve the latest limit for the specified category and month.
     * The data is sorted by the limit setting date in descending order, and only the most recent record is returned.
     * </p>
     *
     * @param month The month to find the limit for (numerical format: 1 for January, 12 for December).
     * @param category The account limit category.
     * @return The most recent {@link AccountLimit} object for the specified month and category,
     *         or {@code null} if no limit is found for this month and category.
     */
    @Query(value = "SELECT * FROM account_limit WHERE EXTRACT(MONTH FROM set_date) = :month AND category = :category ORDER BY set_date DESC LIMIT 1",
            nativeQuery = true)
    AccountLimit findLimitsByMonthAndCategory(@Param("month") int month, @Param("category") String category);
}
