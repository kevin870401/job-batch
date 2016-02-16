package com.otpp.batchJob.service.query.impl;

import com.otpp.batchJob.domain.query.BatchJobLog;
import com.otpp.batchJob.service.ITTestBase;
import com.otpp.batchJob.service.query.BatchJobLogQueryService;
import com.otpp.batchJob.service.query.BatchJobQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;


public class BatchJobLogQueryServiceImplIT extends ITTestBase {

    @Autowired
    BatchJobLogQueryService batchJobLogQueryService;

    @Test
    public void findByJobId_existingId_found() {
        List<BatchJobLog> batchJobLogs= batchJobLogQueryService.findByJobId(1,0,10);
        assertThat(batchJobLogs.size()).isEqualTo(2);
        assertThat(batchJobLogs.get(0).getJobId()).isEqualTo(1);
        assertThat(batchJobLogs.get(1).getJobId()).isEqualTo(1);
    }

    @Test
    public void findByJobId_nonExistingId_empty() {
        List<BatchJobLog> batchJobLogs= batchJobLogQueryService.findByJobId(100,0,10);
        assertThat(batchJobLogs.size()).isEqualTo(0);
    }
}