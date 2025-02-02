package com.example.IDF.technology.task.mapper;

import com.example.IDF.technology.task.dto.AccountLimitDto;
import com.example.IDF.technology.task.entity.AccountLimit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountLimitMapper {

    AccountLimitDto accountLimitToAccountLimitDto(AccountLimit accountLimit);

    AccountLimit accountLimitDtoToAccountLimit(AccountLimitDto accountLimitDto);
}
