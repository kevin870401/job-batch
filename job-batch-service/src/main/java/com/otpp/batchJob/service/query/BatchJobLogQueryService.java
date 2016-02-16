package com.otpp.batchJob.service.query;

import com.otpp.batchJob.domain.query.BatchJob;
import com.otpp.batchJob.domain.query.BatchJobLog;

import java.util.List;

public interface BatchJobLogQueryService {

    List<BatchJobLog> findByJobId(long jobId, int pageNum, int pageSize);
}
