package com.otpp.batchJob.service.command;

import com.otpp.batchJob.domain.config.DomainConfig;
import com.otpp.batchJob.domain.mappers.BatchJobCreateCommandDtoMapper;
import com.otpp.batchJob.domain.mappers.BatchJobLogCreateCommandDtoMapper;
import com.otpp.batchJob.domain.mappers.BatchJobStatusUpdateCommandDtoMapper;
import com.otpp.batchJob.service.command.impl.BatchJobCommandServiceImpl;
import com.otpp.jobBatch.data.repository.BatchJobLogRepository;
import com.otpp.jobBatch.data.repository.BatchJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(DomainConfig.class)
@Configuration
public class CommandServiceConfig {

    @Autowired
    private BatchJobCreateCommandDtoMapper batchJobCreateCommandDtoMapper;

    @Autowired
    private BatchJobLogCreateCommandDtoMapper batchJobLogCreateCommandDtoMapper;

    @Autowired
    private BatchJobStatusUpdateCommandDtoMapper batchJobStatusUpdateCommandDtoMapper;


    @Autowired
    private BatchJobRepository batchJobRepository;

    @Autowired
    private BatchJobLogRepository batchJobLogRepository;


    @Bean
    public BatchJobCommandService BatchJobCommandService(){
        return new BatchJobCommandServiceImpl(batchJobRepository,batchJobLogRepository,batchJobCreateCommandDtoMapper,batchJobLogCreateCommandDtoMapper,batchJobStatusUpdateCommandDtoMapper);
    }
}
