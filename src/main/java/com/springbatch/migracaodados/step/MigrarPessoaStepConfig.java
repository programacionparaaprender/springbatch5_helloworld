package com.springbatch.migracaodados.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.springbatch.migracaodados.dominio.Pessoa;

@Configuration
public class MigrarPessoaStepConfig {

	@Autowired
	@Qualifier("transactionManagerApp")
	private PlatformTransactionManager transactionManagerApp;
	
	@Bean
	public Step migrarPessoaStep(JobRepository jobRepository, ItemReader<Pessoa> arquivoPessoaReader,
			ClassifierCompositeItemWriter<Pessoa> pessoaClassifierWriter,
			ItemProcessor<Pessoa, Pessoa> pessoaProcessor,
			FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter) {
		
		return new StepBuilder("migrarPessoaStep", jobRepository)
				.allowStartIfComplete(true)
				.<Pessoa, Pessoa>chunk(1000, transactionManagerApp)
				.reader(arquivoPessoaReader)
				.processor(pessoaProcessor)
				.writer(pessoaClassifierWriter)
				.stream(arquivoPessoasInvalidasWriter)
				.transactionManager(transactionManagerApp)
				.build();	
	}
}
