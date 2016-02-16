package com.otpp.batchJob.domain.mappers;

import com.otpp.batchJob.domain.query.BatchJob;
import ma.glasnost.orika.MapperFactory;

/**
 * Created by user on 09/02/16.
 */
public class BatchJobMapper extends DefaultMapper<BatchJob, com.otpp.jobBatch.data.entity.BatchJob>{

    public BatchJobMapper(MapperFactory mapperFactory){
        super(mapperFactory);
    }

    @Override
    protected Class<com.otpp.jobBatch.data.entity.BatchJob> dbClass() {
        return com.otpp.jobBatch.data.entity.BatchJob.class;
    }

    @Override
    protected Class<BatchJob> domainClass() {
        return BatchJob.class;
    }
}
