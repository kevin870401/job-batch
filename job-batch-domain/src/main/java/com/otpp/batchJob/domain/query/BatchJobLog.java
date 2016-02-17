package com.otpp.batchJob.domain.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ToString(includeFieldNames = true)
public class BatchJobLog {

    private long id;
    private long jobId;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private String userId;
    private String type;
    private String data;

}
