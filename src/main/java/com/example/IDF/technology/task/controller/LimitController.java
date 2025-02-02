package com.example.IDF.technology.task.controller;

import com.example.IDF.technology.task.dto.AccountLimitDto;
import com.example.IDF.technology.task.service.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limits")
public class LimitController {

    private final LimitService limitService;

    @Autowired
    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }


    @PostMapping("/create")
    public ResponseEntity<Void> createLimit(@RequestBody AccountLimitDto limit) {
        limitService.createAccountLimit(limit);
        return ResponseEntity.ok().build();
    }
}
