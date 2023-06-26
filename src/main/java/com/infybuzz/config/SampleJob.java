package com.infybuzz.config;

import com.infybuzz.listener.FirstJobListener;
import com.infybuzz.processor.FirstItemProcessor;
import com.infybuzz.reader.FirstItemReader;
import com.infybuzz.service.SecondTasklet;
import com.infybuzz.writer.FirstItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;


@Configuration
public class SampleJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private SecondTasklet secondTasklet;

    @Autowired
    private FirstJobListener firstJobListener;

    @Autowired
    private FirstItemReader firstItemReader;

    @Autowired
    private FirstItemProcessor firstItemProcessor;
    @Autowired
    private FirstItemWriter firstItemWriter;

    @Bean
    public Job job(){
        return jobBuilderFactory.get("Job")
                .incrementer(new RunIdIncrementer())
                .start(firstChunkStep())
                .build();
    }
    private Step firstChunkStep(){
        return stepBuilderFactory.get("First BuildStep")
                .<Integer,Long>chunk(3)
                .reader(firstItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWriter)
                .build();

    }

}
