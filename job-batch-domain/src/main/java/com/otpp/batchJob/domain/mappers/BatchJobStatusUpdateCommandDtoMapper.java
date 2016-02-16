package com.otpp.batchJob.domain.mappers;

import com.otpp.batchJob.domain.command.BatchJobCreateCommandDto;
import com.otpp.batchJob.domain.command.BatchJobParameterCreateCommandDto;
import com.otpp.batchJob.domain.command.BatchJobStatusUpdateCommandDto;
import com.otpp.jobBatch.data.entity.BatchJob;
import com.otpp.jobBatch.data.entity.BatchJobParameter;
import com.otpp.jobBatch.data.entity.enums.DossierBatchJobStatus;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.UUID;

public class BatchJobStatusUpdateCommandDtoMapper extends DefaultMapper<BatchJobStatusUpdateCommandDto, BatchJob> {
    @Autowired
    public BatchJobStatusUpdateCommandDtoMapper(MapperFactory mapperFactory) {
        super(mapperFactory);
    }

    @PostConstruct
    public void init() {
        configureMapperFactory();
    }

    @Override
    public BatchJob mapDomainToDb(BatchJobStatusUpdateCommandDto batchJobStatusUpdateCommandDto) {
        BatchJob batchJob = getDefaultMapperFactory().getMapperFacade()
                .map(batchJobStatusUpdateCommandDto, dbClass());
        for (BatchJobParameter p : batchJob.getParameters()) {
            p.setJob(batchJob);
        }
        batchJob.setResult("");
        batchJob.setStatus(DossierBatchJobStatus.NEW);
        batchJob.setUuid(UUID.randomUUID());
        return batchJob;
    }

    @Override
    protected void configureMapperFactory() {
        getDefaultMapperFactory().getConverterFactory()
                .registerConverter(new CustomConverter<BatchJobStatusUpdateCommandDto.BatchJobStatus, DossierBatchJobStatus>() {

                    @Override
                    public DossierBatchJobStatus convert(BatchJobStatusUpdateCommandDto.BatchJobStatus batchJobStatus, Type<? extends DossierBatchJobStatus> type) {

                        return DossierBatchJobStatus.valueOf(batchJobStatus.getJobStatusCode());
                    }
                });


    }

    @Override
    protected Class<BatchJob> dbClass() {
        return BatchJob.class;
    }

    @Override
    protected Class<BatchJobStatusUpdateCommandDto> domainClass() {
        return BatchJobStatusUpdateCommandDto.class;
    }
}
