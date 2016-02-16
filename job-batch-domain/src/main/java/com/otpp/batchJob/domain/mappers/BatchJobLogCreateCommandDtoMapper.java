package com.otpp.batchJob.domain.mappers;

import com.otpp.batchJob.domain.command.BatchJobLogCreateCommandDto;
import com.otpp.jobBatch.data.entity.BatchJobLog;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class BatchJobLogCreateCommandDtoMapper extends DefaultMapper<BatchJobLogCreateCommandDto, BatchJobLog> {
    @Autowired
    public BatchJobLogCreateCommandDtoMapper(MapperFactory mapperFactory) {
        super(mapperFactory);
    }

    @PostConstruct
    public void init() {
        configureMapperFactory();
    }

    @Override
    public BatchJobLog mapDomainToDb(BatchJobLogCreateCommandDto batchJobLogCreateCommandDto) {
        BatchJobLog batchJobLog = getDefaultMapperFactory().getMapperFacade()
                .map(BatchJobLogCreateCommandDto.class, dbClass());
        batchJobLog.setType(batchJobLogCreateCommandDto.getType());
        batchJobLog.setData(batchJobLogCreateCommandDto.getData());
        batchJobLog.setJobId(batchJobLogCreateCommandDto.getJobId());
        return batchJobLog;
    }


    @Override
    protected Class<BatchJobLog> dbClass() {
        return BatchJobLog.class;
    }

    @Override
    protected Class<BatchJobLogCreateCommandDto> domainClass() {
        return BatchJobLogCreateCommandDto.class;
    }
}
