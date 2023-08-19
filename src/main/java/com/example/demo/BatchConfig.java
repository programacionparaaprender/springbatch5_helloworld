package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {
/* 
	@Bean
	Job multithreadingStepJob2(JobRepository jobRepository,
			@Qualifier("migrarPessoaStep")  Step migrarPessoaStep,
			@Qualifier("migrarDadosBancariosStep") Step migrarDadosBancariosStep) {
		return new JobBuilder("multithreadingStepJob2", jobRepository)
				.start(migrarPessoaStep)
				.next(migrarDadosBancariosStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
	*/
	/*
	 * @Bean
	Job multithreadingStepJob2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		
		return new JobBuilder("multithreadingStepJob2", jobRepository)
				.start(migrarDadosBancariosStep(jobRepository, transactionManager, arquivoDadosBancariosReader, bancoDadosBancariosWriter))
				//.next(migrarPessoaStep(jobRepository, transactionManager, arquivoPessoaReader, pessoaClassifierWriter, pessoaProcessor, arquivoPessoasInvalidasWriter))
				.build();			
	}
	@Bean
	public Step migrarPessoaStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, ItemReader<Pessoa> arquivoPessoaReader,
			ClassifierCompositeItemWriter<Pessoa> pessoaClassifierWriter,
			ItemProcessor<Pessoa, Pessoa> pessoaProcessor,
			FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter) {
		
		return new StepBuilder("migrarPessoaStep", jobRepository)
				.allowStartIfComplete(true)
				.<Pessoa, Pessoa>chunk(1000, transactionManager)
				.reader(arquivoPessoaReader)
				.processor(pessoaProcessor)
				.writer(pessoaClassifierWriter)
				.stream(arquivoPessoasInvalidasWriter)
				.transactionManager(transactionManager)
				.build();	
	}
	
	@Bean
	public Step migrarDadosBancariosStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, ItemReader<DadosBancarios> arquivoDadosBancariosReader,
			ItemWriter<DadosBancarios> bancoDadosBancariosWriter) {
		
		return new StepBuilder("migrarDadosBancariosStep", jobRepository)
				.allowStartIfComplete(true)
				.<DadosBancarios, DadosBancarios>chunk(1000, transactionManager)
				.reader(arquivoDadosBancariosReader)
				.writer(bancoDadosBancariosWriter)
				.transactionManager(transactionManager)
				.build();			
	}
	*/
	@Bean
	Step paso1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		
		return new StepBuilder("paso1", jobRepository)
				.allowStartIfComplete(true)
				.tasklet(new Tarea1(), transactionManager)
				.tasklet(new Tarea1(), transactionManager)
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
				.next(paso2(jobRepository, transactionManager))
				.build();			
	}//paso1

}//config
