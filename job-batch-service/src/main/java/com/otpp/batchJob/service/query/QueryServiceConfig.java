package com.otpp.batchJob.service.query;

import com.otpp.batchJob.domain.config.DomainConfig;
import com.otpp.batchJob.domain.mappers.BatchJobLogMapper;
import com.otpp.batchJob.domain.mappers.BatchJobMapper;
import com.otpp.batchJob.service.command.CommandServiceConfig;
import com.otpp.batchJob.service.query.impl.BatchJobLogQueryServiceImpl;
import com.otpp.batchJob.service.query.impl.BatchJobQueryServiceImpl;
import com.otpp.jobBatch.data.repository.BatchJobLogRepository;
import com.otpp.jobBatch.data.repository.BatchJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class QueryServiceConfig {


    @Autowired
    private BatchJobMapper batchJobMapper;

    @Autowired
    private BatchJobLogMapper batchJobLogMapper;

    @Autowired
    private BatchJobRepository batchJobRepository;

    @Autowired
    private BatchJobLogRepository batchJobLogRepository;

    @Bean
    public BatchJobQueryService batchJobQueryService(){
        return new BatchJobQueryServiceImpl(batchJobRepository,batchJobMapper);
    }

    @Bean
    public BatchJobLogQueryService batchJobLogQueryService(){
        return new BatchJobLogQueryServiceImpl(batchJobLogRepository,batchJobLogMapper);
    }
}
