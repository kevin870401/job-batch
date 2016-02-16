package com.otpp.jobBatch.data.entity.enums;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;


public enum DossierBatchJobStatus {

    NEW("NEW"),PND("PND"),RUN("RUN"),FLD("FAILED"),CPT("CPT");

    private String jobStatusCode;
    private DossierBatchJobStatus(String jobStatusCode){
        this.jobStatusCode= jobStatusCode;
    }
    @JsonValue
    public String getJobStatusCode(){
        return jobStatusCode;
    }

    @Override
    public String toString(){
        return getJobStatusCode();
    }
}