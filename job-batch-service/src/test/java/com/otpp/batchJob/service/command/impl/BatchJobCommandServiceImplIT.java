package com.otpp.batchJob.service.command.impl;

import com.otpp.batchJob.domain.command.BatchJobCreateCommandDto;
import com.otpp.batchJob.domain.command.BatchJobLogCreateCommandDto;
import com.otpp.batchJob.domain.command.BatchJobParameterCreateCommandDto;
import com.otpp.batchJob.domain.query.BatchJob;
import com.otpp.batchJob.domain.query.BatchJobLog;
import com.otpp.batchJob.service.ITTestBase;
import com.otpp.batchJob.service.query.BatchJobLogQueryService;
import com.otpp.batchJob.service.query.BatchJobQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BatchJobCommandServiceImplIT extends ITTestBase {
    @Autowired
    com.otpp.batchJob.service.command.BatchJobCommandService batchJobCommandService;
    @Autowired
    BatchJobQueryService batchJobQueryService;

    @Autowired
    BatchJobLogQueryService batchJobLogQueryService;

    private static final String BTCHANNSTATPROTO = "BTCHANNSTATPROTO";
    @Test
    public void createBatchJob_withEmptyParameters_success() throws Exception {
        BatchJobCreateCommandDto batchJobCreateCommandDto= new BatchJobCreateCommandDto();
        batchJobCreateCommandDto.setDescription("this is a new psob job");
        batchJobCreateCommandDto.setPriority((short) 1);
        batchJobCreateCommandDto.setType(BTCHANNSTATPROTO);
        batchJobCreateCommandDto.setParameters(new ArrayList<>());
        long id = batchJobCommandService.createBatchJob(batchJobCreateCommandDto);
        assertThat(id).isGreaterThan(0);
    }

    @Test
    public void createBatchJob_withParameters_success() throws Exception {
        BatchJobParameterCreateCommandDto parameter= new BatchJobParameterCreateCommandDto();
        parameter.setParameterKey("irn");
        parameter.setParameterValue("123456789");
        List<BatchJobParameterCreateCommandDto> parameters=new ArrayList<>();
        parameters.add(parameter);
        BatchJobCreateCommandDto batchJobCreateCommandDto= new BatchJobCreateCommandDto();
        batchJobCreateCommandDto.setDescription("this is a new psob job");
        batchJobCreateCommandDto.setPriority((short) 1);
        batchJobCreateCommandDto.setType(BTCHANNSTATPROTO);
        batchJobCreateCommandDto.setParameters(parameters);
        long id = batchJobCommandService.createBatchJob(batchJobCreateCommandDto);
        assertThat(id).isGreaterThan(0);
        BatchJob batchJob= batchJobQueryService.findById(id);
        assertThat(batchJob.getDescription()).isEqualTo("this is a new psob job");
        assertThat(batchJob.getPriority()).isEqualTo((short)1);
        assertThat(batchJob.getType()).isEqualTo(BTCHANNSTATPROTO);
        assertThat(batchJob.getParameters().get(0).getParameterKey()).isEqualTo("irn");
        assertThat(batchJob.getParameters().get(0).getParameterValue()).isEqualTo("123456789");
    }

    @Test
    public void createBatchJobLog_withValidId_success() throws Exception {

        BatchJobLogCreateCommandDto batchJobLogCreateCommandDto= new BatchJobLogCreateCommandDto();
        batchJobLogCreateCommandDto.setJobId(1);
        batchJobLogCreateCommandDto.setType("APP");
        batchJobLogCreateCommandDto.setData("random");
        batchJobCommandService.insertBatchJobLog(batchJobLogCreateCommandDto);

        List<BatchJobLog> batchJobLogs= batchJobLogQueryService.findByJobId(1,0,10);
        assertThat(batchJobLogs.size()).isEqualTo(3);
    }

}