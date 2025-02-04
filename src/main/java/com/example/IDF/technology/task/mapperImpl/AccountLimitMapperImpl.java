package com.example.IDF.technology.task.mapperImpl;

import com.example.IDF.technology.task.dto.AccountLimitDto;
import com.example.IDF.technology.task.entity.AccountLimit;
import com.example.IDF.technology.task.mapper.AccountLimitMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Implementation of the {@link AccountLimitMapper} interface, providing conversion between
 * {@link AccountLimit} and {@link AccountLimitDto} objects.
 * <p>
 * This component is used for mapping data between layers of the application, such as
 * converting entity data to DTO format and vice versa.
 * It is important to note that the conversion takes into account the possibility of missing data,
 * such as {@link AccountLimitDto#getLimitAmount()}, in which case a default value is used.
 * </p>
 */
@Component
public class AccountLimitMapperImpl implements AccountLimitMapper {

    /**
     * Converts an {@link AccountLimit} object to an {@link AccountLimitDto}.
     * <p>
     * If the provided {@link AccountLimit} object is {@code null}, {@code null} is returned.
     * </p>
     *
     * @param accountLimit The object containing account limit data.
     * @return An {@link AccountLimitDto} object corresponding to the data in {@link AccountLimit},
     *         or {@code null} if the input object is {@code null}.
     */
    @Override
    public AccountLimitDto accountLimitToAccountLimitDto(AccountLimit accountLimit) {
        if (accountLimit == null) {
            return null;
        }
        return new AccountLimitDto(accountLimit.getLimitAmount(),
                accountLimit.getCategory());
    }

    /**
     * Converts an {@link AccountLimitDto} object to an {@link AccountLimit}.
     * <p>
     * If the provided {@link AccountLimitDto} object is {@code null}, {@code null} is returned.
     * If the DTO does not contain a value for the limit, an {@link AccountLimit} object is created with a zero limit.
     * </p>
     *
     * @param accountLimitDto The object containing account limit data in DTO format.
     * @return An {@link AccountLimit} object corresponding to the data in {@link AccountLimitDto},
     *         or {@code null} if the input object is {@code null}.
     */
    @Override
    public AccountLimit accountLimitDtoToAccountLimit(AccountLimitDto accountLimitDto) {
        if (accountLimitDto == null) {
            return null;
        }
        if (accountLimitDto.getLimitAmount() == null) {
            return new AccountLimit(accountLimitDto.getCategory(),
                    LocalDateTime.now());
        }
        return new AccountLimit(accountLimitDto.getCategory(),
                accountLimitDto.getLimitAmount(),
                LocalDateTime.now());
    }
}
