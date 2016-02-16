package com.otpp.batchJob.domain.mappers;

import com.otpp.batchJob.domain.query.BatchJobLog;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BatchJobLogMapper extends DefaultMapper<BatchJobLog, com.otpp.jobBatch.data.entity.BatchJobLog> {
    @Autowired
    public BatchJobLogMapper(MapperFactory mapperFactory) {
        super(mapperFactory);
    }

    @Override
    protected Class<com.otpp.jobBatch.data.entity.BatchJobLog> dbClass() {
        return com.otpp.jobBatch.data.entity.BatchJobLog.class;
    }

    @Override
    protected Class<BatchJobLog> domainClass() {
        return BatchJobLog.class;
    }
}
