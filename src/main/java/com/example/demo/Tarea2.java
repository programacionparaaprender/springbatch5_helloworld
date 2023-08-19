package com.example.demo;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Tarea2 implements Tasklet{

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Hola desde Tarea 2");
		log.info("Hola desde Tarea 2");
		
		return RepeatStatus.FINISHED;
		
	}

}
