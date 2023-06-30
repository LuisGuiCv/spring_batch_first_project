package com.infybuzz.app;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({"com.infybuzz.config","com.infybuzz.controller","com.infybuzz.listener","com.infybuzz.processor"
		,"com.infybuzz.reader","com.infybuzz.service","com.infybuzz.writer"})
@EnableAsync
public class BatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

}
