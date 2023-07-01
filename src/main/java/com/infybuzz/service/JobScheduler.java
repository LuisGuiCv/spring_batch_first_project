package com.infybuzz.service;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JobScheduler {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;
    @Scheduled(cron="0 0/1 * 1/1 * ?")
    public void secondJobStarter(){
        Map<String, JobParameter> params=new HashMap<>();
        params.put("currentTime: ",new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameter=new JobParameters(params);
        try {
            JobExecution jobExecution= null;
                jobExecution=   jobLauncher.run(job, jobParameter);

            System.out.println("job Execution ID= "+jobExecution.getJobId());
        } catch (JobInstanceAlreadyCompleteException e) {
            throw new RuntimeException(e);
        } catch (JobExecutionAlreadyRunningException e) {
            throw new RuntimeException(e);
        } catch (JobParametersInvalidException e) {
            throw new RuntimeException(e);
        } catch (JobRestartException e) {
            throw new RuntimeException(e);
        }
    }

}
