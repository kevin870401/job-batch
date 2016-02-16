package com.otpp.batchJob.domain.command;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(includeFieldNames = true)
public class BatchJobStatusUpdateCommandDto {
    private long id;
    private BatchJobStatus batchJobStatus;

    public enum BatchJobStatus {

        NEW("new"),PND("pending"),RUN("run"),FLD("failed"),CPT("complete");

        private String jobStatusCode;
        private BatchJobStatus(String jobStatusCode){
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

}
