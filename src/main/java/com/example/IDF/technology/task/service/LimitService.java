package com.example.IDF.technology.task.service;

import java.util.logging.Logger;

import com.example.IDF.technology.task.dto.AccountLimitDto;
import com.example.IDF.technology.task.entity.AccountLimit;
import com.example.IDF.technology.task.mapper.AccountLimitMapper;
import com.example.IDF.technology.task.repository.LimitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing account limits.
 * <p>
 * This service provides methods to create and manage account limits. It interacts with the
 * {@link LimitRepository} for persistence operations and uses {@link AccountLimitMapper} to convert
 * between DTO and entity objects.
 * </p>
 */
@Service
public class LimitService {

    private static final Logger logger = Logger.getLogger(LimitService.class.getName());

    private final LimitRepository limitRepository;

    private final AccountLimitMapper accountLimitMapper;

    /**
     * Constructor to initialize the service with the required repository and mapper.
     *
     * @param limitRepository The repository for managing account limits.
     * @param accountLimitMapper The mapper for converting between DTO and entity.
     */
    @Autowired
    public LimitService(LimitRepository limitRepository, AccountLimitMapper accountLimitMapper) {
        this.limitRepository = limitRepository;
        this.accountLimitMapper = accountLimitMapper;
    }

    /**
     * Creates a new account limit based on the provided {@link AccountLimitDto}.
     * <p>
     * The method converts the provided DTO to an entity using the mapper, then saves the entity
     * to the repository. It is marked as transactional, meaning the operation will be rolled back
     * if an exception occurs during the process.
     * </p>
     *
     * @param accountLimitDto The DTO containing the account limit data to be created.
     */
    @Transactional
    public void createAccountLimit(AccountLimitDto accountLimitDto) {
        logger.info("Creating account limit: " + accountLimitDto);
        AccountLimit accountLimit = accountLimitMapper.accountLimitDtoToAccountLimit(accountLimitDto);
        limitRepository.save(accountLimit);
        logger.info("Account limit saved: " + accountLimit);
    }
}
