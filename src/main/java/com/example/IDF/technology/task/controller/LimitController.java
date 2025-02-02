package com.example.IDF.technology.task.controller;

import com.example.IDF.technology.task.dto.AccountLimitDto;
import com.example.IDF.technology.task.service.LimitService;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limits")
public class LimitController {

    private static final Logger logger = Logger.getLogger(LimitController.class.getName());  // Используем стандартный Logger

    private final LimitService limitService;

    @Autowired
    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createLimit(@RequestBody AccountLimitDto limit) {
        logger.info("Received request to create account limit: " + limit);  // Логирование входящих данных
        try {
            limitService.createAccountLimit(limit);
            logger.info("Successfully created account limit for: " + limit);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.severe("Error occurred while creating account limit for: " + limit + " Error: " + e.getMessage());  // Логирование ошибки
            return ResponseEntity.status(500).build();
        }
    }
}
