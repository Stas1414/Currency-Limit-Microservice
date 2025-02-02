package com.example.IDF.technology.task.mapperImpl;

import com.example.IDF.technology.task.dto.AccountLimitDto;
import com.example.IDF.technology.task.entity.AccountLimit;
import com.example.IDF.technology.task.mapper.AccountLimitMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AccountLimitMapperImpl implements AccountLimitMapper {
    @Override
    public AccountLimitDto accountLimitToAccountLimitDto(AccountLimit accountLimit) {
        if (accountLimit == null) {
            return null;
        }
        return new AccountLimitDto(accountLimit.getLimitAmount(),
                accountLimit.getCategory());
    }

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
