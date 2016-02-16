package com.otpp.batchJob.domain.query;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString(includeFieldNames = true)
public class BatchJobLog {

    private long id;
    private long jobId;
    private Date timestamp;
    private String userId;
    private String type;
    private String data;
}
