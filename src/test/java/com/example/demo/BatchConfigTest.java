package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


@SpringBatchTest
@SpringJUnitConfig(BatchConfig.class)
public class BatchConfigTest {

	@Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Test
    public void testJob(@Autowired Job job) throws Exception {
        this.jobLauncherTestUtils.setJob(job);
        

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();


        Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
    }
	
	@Test
	public void noWork() {
	    StepExecution stepExecution = new StepExecution("NoProcessingStep",
	                new JobExecution(new JobInstance(1L, new JobParameters(),
	                                 "NoProcessingJob")));

	    stepExecution.setExitStatus(ExitStatus.COMPLETED);
	    stepExecution.setReadCount(0);

	    ExitStatus exitStatus = tested.afterStep(stepExecution);
	    assertEquals(ExitStatus.FAILED.getExitCode(), exitStatus.getExitCode());
	}
}
