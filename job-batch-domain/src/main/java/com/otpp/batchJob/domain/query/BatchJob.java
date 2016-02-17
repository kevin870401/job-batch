package com.otpp.batchJob.domain.query;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class BatchJob {
    public enum BatchJobStatus {

        NEW("new"), PND("pending"), RUN("run"), FLD("failed"), CPT("complete");

        private String jobStatusCode;

        private BatchJobStatus(String jobStatusCode) {
            this.jobStatusCode = jobStatusCode;
        }

        @JsonValue
        public String getJobStatusCode() {
            return jobStatusCode;
        }

        @Override
        public String toString() {
            return getJobStatusCode();
        }
    }


    private long id;
    private String uuid;
    private String type;
    private short priority;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm:ss")
    private Date createTime;
    private String description;
    private BatchJobStatus status;
    private String result;
    private List<BatchJobParameter> parameters;

    @Override
    public String toString() {
        StringBuilder parameterToString = new StringBuilder();
        this.parameters.forEach(parameterToString::append);
        return "BatchJob(" + this.getId() + ", " + this.getUuid() + ", " + this.getType() + ", " + this.getPriority() + ", " +this.getCreateTime() + ", " +this.getDescription() + ", " +this.getPriority() + ", " +this.getStatus() + ", " + parameterToString + ")";
    }

}
