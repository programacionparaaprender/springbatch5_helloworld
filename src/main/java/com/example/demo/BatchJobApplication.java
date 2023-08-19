package com.example.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class BatchJobApplication {

	public static void main(String[] args) {
		log.info("Funciona log4j");
		SpringApplication.run(BatchJobApplication.class, args);
	}

}
