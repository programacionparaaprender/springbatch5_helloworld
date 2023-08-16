package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {
	
		@Bean
		Step paso1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
			
			return new StepBuilder("paso1", jobRepository)
					.allowStartIfComplete(true)
					.tasklet(new Tarea1(), transactionManager)
					.build();			
		}//paso1
		
		@Bean
		Step paso2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
			
			return new StepBuilder("paso2", jobRepository)
					.allowStartIfComplete(true)
					.tasklet(new Tarea2(), transactionManager)
					.build();			
		}//paso2
		
		@Bean
		Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
			
			return new JobBuilder("job", jobRepository)
					.start(paso1(jobRepository, transactionManager))
					.next(paso2(jobRepository, transactionManager))
					.build();			
		}//paso1

}//config
