package com.example.IDF.technology.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IdfTechnologyTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdfTechnologyTaskApplication.class, args);
	}

}
