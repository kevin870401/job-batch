package com.otpp.batchJob.domain.command;


import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(includeFieldNames = true)
public class BatchJobLogCreateCommandDto {
    private long jobId;
    private String type;
    private String data;

}
