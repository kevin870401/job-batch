package com.otpp.batchJob.service.query.impl;

import com.otpp.batchJob.domain.query.BatchJob;
import com.otpp.batchJob.service.ITTestBase;
import com.otpp.batchJob.service.ServiceITContext;
import com.otpp.batchJob.service.query.BatchJobQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

@Transactional
public class BatchJobQueryServiceImplIT extends ITTestBase{

    private static final String BTCHANNSTATPROTO = "BTCHANNSTATPROTO";
    private static final String ANOTHERTYPE = "ANOTHERTYPE";
    private static final String STATUS_PENDING = "PND";
    private static final String STATUS_NEW = "NEW";
    @Autowired
    BatchJobQueryService batchJobQueryService;

    @Test
    public void findByStatusOrType_pendingPsobJob_four() throws Exception {
        List<BatchJob> result= batchJobQueryService.findByStatusOrType(STATUS_PENDING,BTCHANNSTATPROTO,0,10);
        assertThat(result.size()).isEqualTo(4);
    }

    @Test
    public void findByStatusOrType_newOtherJob_one() throws Exception {
        List<BatchJob> result= batchJobQueryService.findByStatusOrType(STATUS_PENDING,ANOTHERTYPE,0,10);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void findById_existingId_success(){
        BatchJob job= batchJobQueryService.findById(1);
        assertThat(job.getId()).isEqualTo(1);
    }
}