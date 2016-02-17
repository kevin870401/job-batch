package com.otpp.batchJob.service.command.impl;

import com.otpp.batchJob.domain.command.BatchJobCreateCommandDto;
import com.otpp.batchJob.domain.command.BatchJobLogCreateCommandDto;
import com.otpp.batchJob.domain.command.BatchJobStatusUpdateCommandDto;
import com.otpp.batchJob.domain.mappers.BatchJobCreateCommandDtoMapper;
import com.otpp.batchJob.domain.mappers.BatchJobLogCreateCommandDtoMapper;
import com.otpp.batchJob.domain.mappers.BatchJobStatusUpdateCommandDtoMapper;
import com.otpp.batchJob.service.command.BatchJobCommandService;
import com.otpp.jobBatch.data.repository.BatchJobLogRepository;
import com.otpp.jobBatch.data.repository.BatchJobRepository;


public class BatchJobCommandServiceImpl implements BatchJobCommandService {

    private BatchJobRepository batchJobRepository;
    private BatchJobCreateCommandDtoMapper batchJobCreateCommandDtoMapper;

    private BatchJobLogRepository batchJobLogRepository;
    private BatchJobLogCreateCommandDtoMapper batchJobLogCreateCommandDtoMapper;
    private BatchJobStatusUpdateCommandDtoMapper batchJobStatusUpdateCommandDtoMapper;


    public BatchJobCommandServiceImpl(BatchJobRepository batchJobRepository, BatchJobLogRepository batchJobLogRepository, BatchJobCreateCommandDtoMapper batchJobCreateCommandDtoMapper, BatchJobLogCreateCommandDtoMapper batchJobLogCreateCommandDtoMapper, BatchJobStatusUpdateCommandDtoMapper batchJobStatusUpdateCommandDtoMapper) {
        this.batchJobRepository = batchJobRepository;
        this.batchJobCreateCommandDtoMapper = batchJobCreateCommandDtoMapper;
        this.batchJobLogRepository = batchJobLogRepository;
        this.batchJobLogCreateCommandDtoMapper = batchJobLogCreateCommandDtoMapper;
        this.batchJobStatusUpdateCommandDtoMapper = batchJobStatusUpdateCommandDtoMapper;
    }

    @Override
    public long createBatchJob(BatchJobCreateCommandDto batchJobCreateCommandDto) {
        long id = batchJobRepository.saveAndFlush(batchJobCreateCommandDtoMapper.mapDomainToDb(batchJobCreateCommandDto)).getId();
        return id;
    }

    @Override
    public void updateBatchJobStatus(BatchJobStatusUpdateCommandDto batchJobStatusUpdateCommandDto) {

        batchJobRepository.updateJobStatus(batchJobStatusUpdateCommandDto.getId(), batchJobStatusUpdateCommandDtoMapper.mapDomainToDb(batchJobStatusUpdateCommandDto) );
    }

    @Override
    public void insertBatchJobLog(BatchJobLogCreateCommandDto batchJobLogCreateCommandDto) {
        batchJobLogRepository.saveAndFlush(batchJobLogCreateCommandDtoMapper.mapDomainToDb(batchJobLogCreateCommandDto));
    }

}
