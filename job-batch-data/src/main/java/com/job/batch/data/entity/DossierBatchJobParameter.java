package com.job.batch.data.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


//ID IDENTITY,
//        JOB_ID BIGINT NOT NULL,
//        LOG_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
//        LOG_USERID VARCHAR(25) NOT NULL DEFAULT CURRENT_USER(),
//        LOG_TYPE VARCHAR(50) NOT NULL,
//        LOG_DATA VARCHAR(5000) NOT NULL,

@Getter
@Setter
@ToString(includeFieldNames = true)
@Entity
@Table(name = "DOSSIER_BATCH_PARAM")
public class DossierBatchJobParameter implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "JOB_ID")
    private DossierBatchJob job;

    @Column(name = "PARAM_KEY")
    private String parameterKey;

    @Column(name = "PARAM_VAL")
    private String parameterValue;

    @Override
    public String toString() {
        return "DossierBatchJobParameter(" + this.getId() + ", " + this.getParameterKey() + ", " + this.getParameterValue() + ")";
    }
    @Override
    public boolean equals(Object object) {
        boolean result= true;
        if (this == object) {
            result= true;
        }else {

            if (!(object instanceof DossierBatchJobParameter)) {
                result = false;
            } else {
                DossierBatchJobParameter other = (DossierBatchJobParameter) object;
                if (other.getId() != this.getId() || !other.getParameterKey().equals( this.getParameterKey()) || !other.getParameterValue().equals( this.getParameterValue())) {
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
        result = prime * result + ((parameterKey == null) ? 0 : parameterKey.hashCode());
        result = prime * result + ((parameterValue == null) ? 0 : parameterValue.hashCode());
        return result;
    }


}
