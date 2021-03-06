package com.otpp.jobBatch.data.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString(includeFieldNames = true)
@Entity
@Table(name = "DOSSIER_BATCH_LOG")
public class BatchJobLog implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //we don't do eager fetch to job object here since user would like to view log separately other than in the same page
    @Column(name = "JOB_ID")
    private long jobId;

    @Column(name = "LOG_TIMESTAMP",insertable = false, updatable = false,nullable=false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date timestamp;

    @Column(name = "LOG_USERID",insertable = false, updatable = false,nullable=false,columnDefinition="VARCHAR DEFAULT CURRENT_USER")
    private String userId;

    @Column(name = "LOG_TYPE")
    private String type;

    @Column(name = "LOG_DATA")
    private String data;

    @Override
    public String toString() {
        return String.join(
                "BatchJob(" ,
                String.valueOf(this.getId()) , ", " ,
                String.valueOf(this.getJobId()) , ", " ,
                this.getTimestamp().toString() , ", " ,
                this.getType() , ", " ,
                String.valueOf(this.getUserId()) , ", " ,
                this.getData(),")");
    }

    @Override
    public boolean equals(Object object) {
        boolean result= true;
        if (this == object) {
            result= true;
        }else {

            if (!(object instanceof BatchJobLog)) {
                result = false;
            } else {
                BatchJobLog other = (BatchJobLog) object;
                if (other.getId() != this.getId() || other.getJobId()!=( this.getJobId())|| !other.getTimestamp().equals( this.getTimestamp()) || !other.getType().equals( this.getType())|| other.getUserId()!=( this.getUserId())|| !other.getData().equals( this.getData())) {
                    result = false;
                }
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + new Long(id).hashCode();
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + new Long(jobId).hashCode() ;
        result = prime * result + new Long(userId).hashCode() ;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        return result;
    }


}
