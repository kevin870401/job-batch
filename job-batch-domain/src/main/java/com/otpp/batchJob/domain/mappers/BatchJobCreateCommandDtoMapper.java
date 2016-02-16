package com.otpp.batchJob.domain.mappers;

import com.otpp.batchJob.domain.command.BatchJobCreateCommandDto;
import com.otpp.batchJob.domain.command.BatchJobParameterCreateCommandDto;
import com.otpp.jobBatch.data.entity.BatchJob;
import com.otpp.jobBatch.data.entity.BatchJobParameter;
import com.otpp.jobBatch.data.entity.enums.DossierBatchJobStatus;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.UUID;

public class BatchJobCreateCommandDtoMapper extends DefaultMapper<BatchJobCreateCommandDto, BatchJob> {
    @Autowired
    public BatchJobCreateCommandDtoMapper(MapperFactory mapperFactory) {
        super(mapperFactory);
    }

    @PostConstruct
    public void init() {
        configureMapperFactory();
    }

    @Override
    public BatchJob mapDomainToDb(BatchJobCreateCommandDto batchJobCreateCommandDto) {
        BatchJob batchJob = getDefaultMapperFactory().getMapperFacade()
                .map(batchJobCreateCommandDto, dbClass());
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
                .registerConverter(new CustomConverter<BatchJobParameterCreateCommandDto, BatchJobParameter>() {

                    public BatchJobParameter convert(BatchJobParameterCreateCommandDto batchJobParameterCreateCommandDto,
                                          Type<? extends BatchJobParameter> destinationClass) {
                        BatchJobParameter batchJobParameter= new BatchJobParameter();
                        batchJobParameter.setParameterKey(batchJobParameterCreateCommandDto.getParameterKey());
                        batchJobParameter.setParameterValue(batchJobParameterCreateCommandDto.getParameterValue());
                        return batchJobParameter;
                    }
                });

//        getDefaultMapperFactory().getConverterFactory()
//                .registerConverter(new CustomConverter<String, InternalReferenceNumber>() {
//
//                    public InternalReferenceNumber convert(String irn,
//                                                           Type<? extends InternalReferenceNumber> destinationClass) {
//                        return InternalReferenceNumber.valueOf(irn);
//                    }
//                });
    }

    @Override
    protected Class<BatchJob> dbClass() {
        return BatchJob.class;
    }

    @Override
    protected Class<BatchJobCreateCommandDto> domainClass() {
        return BatchJobCreateCommandDto.class;
    }
}
