package com.otpp.batchJob.service.command;


import com.otpp.batchJob.domain.command.BatchJobCreateCommandDto;
import com.otpp.batchJob.domain.command.BatchJobLogCreateCommandDto;
import com.otpp.batchJob.domain.command.BatchJobStatusUpdateCommandDto;

public interface BatchJobCommandService {
    long createBatchJob(BatchJobCreateCommandDto batchJobCreateCommandDto);
    void updateBatchJobStatus(BatchJobStatusUpdateCommandDto batchJobStatusUpdateCommandDto);
    void insertBatchJobLog(BatchJobLogCreateCommandDto batchJobLogCreateCommandDto);
}
