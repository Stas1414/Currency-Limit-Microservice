package com.example.IDF.technology.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients(basePackages = "com.example.IDF.technology.task.feign")
public class IdfTechnologyTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdfTechnologyTaskApplication.class, args);
	}

}
