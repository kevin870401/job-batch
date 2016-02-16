package com.otpp.batchJob.service.query;

import com.otpp.batchJob.domain.query.BatchJob;

import java.util.List;

public interface BatchJobQueryService {

    List<BatchJob> findByStatusOrType(String status, String type,int pageNum,int pageSize);
    BatchJob findById(long id);
    Long countByStatusOrType(String status, String type);
}
