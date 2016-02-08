package com.job.batch.data.entity;


import com.job.batch.data.entity.enums.DossierBatchJobStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import com.google.common.collect.Iterables;

@Getter
@Setter
@ToString(includeFieldNames = true)
@Entity
//@EqualsAndHashCode
@Table(name = "DOSSIER_BATCH_JOB")
public class DossierBatchJob implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "JOB_TYPE")
    private String type;

    @Column(name = "PRIORITY")
    private String priority;

    @Column(name = "CREATE_TIMESTAMP",insertable = false, updatable = false)
    private String createTime;

    @Column(name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DossierBatchJobStatus status;

    @Column(name = "RESULT")
    private String result;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "job")
    @OrderBy("id ASC")
    private List<DossierBatchJobParameter> parameters;

    @Override
    public String toString() {
        return String.join("DossierBatchJob(" , String.valueOf(this.getId()) , ", " ,this.getUuid() + ", " , this.getType() + ", " , this.getPriority() + ", " , this.getCreateTime() + ", " , this.getDescription() + ", " , this.getResult() + ", " , this.getParameters().toString(),")");
    }



    @Override
    public boolean equals(Object object) {
        boolean result = true;
        if (this == object) {
            result = true;
        } else {

            if (!(object instanceof DossierBatchJob)) {
                result = false;
            } else {
                DossierBatchJob other = (DossierBatchJob) object;
                if (other.getId() != this.getId() || !other.getUuid().equals( this.getUuid())
                        || !other.getDescription().equals( this.getDescription())
                        || !other.getPriority().equals(this.getPriority())
                        || !other.getType().equals(this.getType())
                        || !other.getCreateTime().equals(this.getCreateTime())
                        || !other.getResult().equals(this.getResult())
                        || !other.getStatus().equals(this.getStatus())
                        || !Iterables.elementsEqual(other.getParameters(), this.getParameters())) {
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
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((priority == null) ? 0 : priority.hashCode());
        result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
        result = prime * result + ((this.parameters == null) ? 0 : this.parameters.hashCode());

        return result;
    }
}
