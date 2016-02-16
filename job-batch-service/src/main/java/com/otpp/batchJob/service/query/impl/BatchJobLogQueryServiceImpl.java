package com.otpp.batchJob.service.query.impl;


import com.otpp.batchJob.domain.mappers.BatchJobLogMapper;
import com.otpp.batchJob.domain.query.BatchJobLog;
import com.otpp.batchJob.service.query.BatchJobLogQueryService;
import com.otpp.jobBatch.data.repository.BatchJobLogRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class BatchJobLogQueryServiceImpl implements BatchJobLogQueryService {

    private BatchJobLogRepository batchJobLogRepository;
    private BatchJobLogMapper batchJobLogMapper;

    public BatchJobLogQueryServiceImpl(BatchJobLogRepository batchJobLogRepository, BatchJobLogMapper batchJobLogMapper){
        this.batchJobLogRepository= batchJobLogRepository;
        this.batchJobLogMapper= batchJobLogMapper;
    }

    @Override
    public List<BatchJobLog> findByJobId(long jobId, int pageNum, int pageSize) {
        Pageable pageable= new PageRequest(pageNum,pageSize,new Sort(new Sort.Order(Sort.Direction.ASC, "id")));

        return batchJobLogMapper.mapDbToDomain(batchJobLogRepository.findByJobId(jobId,pageable));
    }
}
