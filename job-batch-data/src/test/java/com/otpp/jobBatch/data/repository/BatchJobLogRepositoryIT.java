package com.otpp.jobBatch.data.repository;

import com.otpp.jobBatch.data.entity.BatchJobLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class BatchJobLogRepositoryIT extends ITTestBase {
    private Pageable pageable= new PageRequest(0,10,new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
    @Autowired
    private BatchJobLogRepository batchJobLogRepository;
    @Test
    public void findByJobId_AnExistingJobId_success(){
        List<BatchJobLog> result = batchJobLogRepository.findByJobId(1, pageable);
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getUserId()).isEqualTo("SA");
        assertThat(result.get(0).getType()).isEqualTo("APP");
        assertThat(result.get(0).getData()).isEqualTo("this is a log generated from APP");
        assertThat(result.get(0).getJobId()).isEqualTo(1);
    }

    @Test
    public void findByJobId_ANonExistingJobId_zeroFound(){
        List<BatchJobLog> result = batchJobLogRepository.findByJobId(99, pageable);
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    public void saveAndFlush_persistToAnExisingJob_success(){
        BatchJobLog batchJobLog = new BatchJobLog();
        batchJobLog.setType("App");
        batchJobLog.setData("new log");
        batchJobLog.setJobId(1);
        BatchJobLog result= batchJobLogRepository.saveAndFlush(batchJobLog);
        assertThat(result.getId()).isNotNull();
    }

    @Test(expectedExceptions = DataIntegrityViolationException.class)
    public void saveAndFlush_persistToANonExistingJob_failed(){
        BatchJobLog batchJobLog = new BatchJobLog();
        batchJobLog.setType("App");
        batchJobLog.setData("new log");
        batchJobLog.setJobId(99);
        batchJobLogRepository.saveAndFlush(batchJobLog);
    }

}
