package com.example.IDF.technology.task.service;

import java.util.logging.Logger;

import com.example.IDF.technology.task.dto.AccountLimitDto;
import com.example.IDF.technology.task.entity.AccountLimit;
import com.example.IDF.technology.task.mapper.AccountLimitMapper;
import com.example.IDF.technology.task.repository.LimitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LimitService {

    private static final Logger logger = Logger.getLogger(LimitService.class.getName());

    private final LimitRepository limitRepository;

    private final AccountLimitMapper accountLimitMapper;

    @Autowired
    public LimitService(LimitRepository limitRepository, AccountLimitMapper accountLimitMapper) {
        this.limitRepository = limitRepository;
        this.accountLimitMapper = accountLimitMapper;
    }

    @Transactional
    public void createAccountLimit(AccountLimitDto accountLimitDto) {
        logger.info("Creating account limit: " + accountLimitDto);
        AccountLimit accountLimit = accountLimitMapper.accountLimitDtoToAccountLimit(accountLimitDto);
        limitRepository.save(accountLimit);
        logger.info("Account limit saved: " + accountLimit);
    }
}
