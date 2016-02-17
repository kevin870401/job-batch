package com.otpp.batchJob.domain.config;

import com.otpp.batchJob.domain.mappers.*;
import com.otpp.jobBatch.data.config.DataContext;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class DomainConfig {
    @Bean
    public MapperFactory defaultMapperFactory() {
        return new DefaultMapperFactory.Builder().build();
    }

    @Bean
    public BatchJobMapper batchJobMapper(){
        return new BatchJobMapper(defaultMapperFactory());
    }

    @Bean
    public BatchJobLogMapper batchJobLogMapper(){
        return new BatchJobLogMapper(defaultMapperFactory());
    }

    @Bean
    public BatchJobCreateCommandDtoMapper batchJobCreateCommandDtoMapper(){
        return new BatchJobCreateCommandDtoMapper(defaultMapperFactory());
    }

    @Bean
    public BatchJobLogCreateCommandDtoMapper batchJobLogCreateCommandDtoMapper(){
        return new BatchJobLogCreateCommandDtoMapper(defaultMapperFactory());
    }

    @Bean
    public BatchJobStatusUpdateCommandDtoMapper batchJobStatusUpdateCommandDtoMapper(){
        return new BatchJobStatusUpdateCommandDtoMapper(defaultMapperFactory());
    }
}
