package com.job.batch.ws.config;

import com.job.batch.ws.controller.BatchJobCommandController;
import com.job.batch.ws.controller.BatchJobQueryController;
import com.otpp.batchJob.service.command.BatchJobCommandService;
import com.otpp.batchJob.service.query.BatchJobLogQueryService;
import com.otpp.batchJob.service.query.BatchJobQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ControllerConfiguration {
    @Autowired
    private BatchJobQueryService batchJobQueryService;
    @Autowired
    private BatchJobLogQueryService batchJobLogQueryService;

    @Autowired
    private BatchJobCommandService batchJobCommandService;

    @Bean
    public BatchJobQueryController batchJobQueryController() {
        BatchJobQueryController controller = new BatchJobQueryController(batchJobQueryService,batchJobLogQueryService);
        return controller;
    }

    @Bean
    BatchJobCommandController batchJobCommandController(){
        BatchJobCommandController controller= new BatchJobCommandController(batchJobCommandService);
        return controller;
    }
}