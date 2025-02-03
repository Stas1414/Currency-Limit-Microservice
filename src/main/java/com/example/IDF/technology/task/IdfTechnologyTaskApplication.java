package com.example.IDF.technology.task;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@OpenAPIDefinition(
        info = @Info(title = "Bank Transactions API", version = "1.0", description = "Документация API для управления транзакциями")
)
@SpringBootApplication
@EnableScheduling
@EnableFeignClients(basePackages = "com.example.IDF.technology.task.feign")
public class IdfTechnologyTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdfTechnologyTaskApplication.class, args);
    }

}
