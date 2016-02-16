package com.otpp.batchJob.domain.command;

import com.otpp.batchJob.domain.query.BatchJob;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class BatchJobParameterCreateCommandDto {
    private BatchJob job;

    private String parameterKey;

    private String parameterValue;
}
