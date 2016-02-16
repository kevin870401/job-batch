package com.otpp.batchJob.domain.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

@Data
public class BatchJobParameter {
    private long id;
    @JsonIgnore
    private BatchJob job;

    private String parameterKey;

    private String parameterValue;

    @Override
    public String toString() {

        return "BatchJobParameter (" + this.getId() + ", " + this.getParameterKey() + ", " + this.getParameterValue() +")";
    }
}
