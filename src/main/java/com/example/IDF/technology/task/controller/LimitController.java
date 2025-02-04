package com.example.IDF.technology.task.controller;

import com.example.IDF.technology.task.dto.AccountLimitDto;
import com.example.IDF.technology.task.service.LimitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller for managing account limits.
 * Provides an endpoint to create account limits.
 */
@RestController
@RequestMapping("/api/limits")
@Tag(name = "Account Limits", description = "API for managing account limits")
public class LimitController {

    private static final Logger logger = LoggerFactory.getLogger(LimitController.class);
    private final LimitService limitService;

    /**
     * Constructor to initialize the controller with a {@link LimitService} instance.
     *
     * @param limitService the service responsible for handling account limits
     */
    @Autowired
    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }

    /**
     * Creates an account limit.
     * Accepts an account limit object, processes the creation, and returns a response.
     *
     * @param limit the account limit data to be created
     * @return a {@link ResponseEntity} indicating the result of the operation
     */
    @Operation(summary = "Create an account limit", description = "Accepts an account limit and processes its creation.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account limit successfully created"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    public ResponseEntity<Void> createLimit(@RequestBody AccountLimitDto limit) {
        logger.info("Received request to create account limit: {}", limit);
        try {
            limitService.createAccountLimit(limit);
            logger.info("Account limit created successfully for: {}", limit);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error occurred while creating account limit for: {}. Error: {}", limit, e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}
