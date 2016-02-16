package com.otpp.batchJob.domain.command;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString(includeFieldNames = true)
public class BatchJobCreateCommandDto {

    private String type;
    private short priority;
    private String description;
    private List<BatchJobParameterCreateCommandDto> parameters;

}
