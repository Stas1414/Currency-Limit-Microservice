package com.example.IDF.technology.task.mapper;

import com.example.IDF.technology.task.dto.AccountLimitDto;
import com.example.IDF.technology.task.entity.AccountLimit;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between {@link AccountLimit} and {@link AccountLimitDto}.
 * Uses the MapStruct library to automatically generate mapping methods.
 *
 * <p>Example usage:</p>
 * <pre>
 *     AccountLimit accountLimit = new AccountLimit();
 *     AccountLimitDto dto = accountLimitMapper.accountLimitToAccountLimitDto(accountLimit);
 * </pre>
 *
 * @author (Your Name)
 */
@Mapper
public interface AccountLimitMapper {

    /**
     * Converts an {@link AccountLimit} entity to an {@link AccountLimitDto}.
     *
     * @param accountLimit The entity to be converted.
     * @return A DTO object containing data from the {@link AccountLimit} entity.
     */
    AccountLimitDto accountLimitToAccountLimitDto(AccountLimit accountLimit);

    /**
     * Converts an {@link AccountLimitDto} to an {@link AccountLimit} entity.
     *
     * @param accountLimitDto The DTO object to be converted.
     * @return An {@link AccountLimit} entity containing data from the DTO.
     */
    AccountLimit accountLimitDtoToAccountLimit(AccountLimitDto accountLimitDto);
}
