package com.job.batch.data.repository;

import com.job.batch.data.ITContext;
import com.job.batch.data.entity.DossierBatchJob;
import com.job.batch.data.entity.DossierBatchJobParameter;
import com.job.batch.data.entity.enums.DossierBatchJobStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ContextConfiguration(classes = ITContext.class)
@Transactional
public class BatchJobRepositoryIT extends ITTestBase {
    private static final int PAGE_SIZE = 50;
    public static final String UUID1 = "10000001";
    public static final String BTCHANNSTATPROTO = "BTCHANNSTATPROTO";
    public static final String FIRST_PRIORITY = "1";
    public static final String A_SIMPLE_PSOB_TASK = "a simple psob task";
    private Pageable pageable= new PageRequest(0,10,new Sort(new Sort.Order(Sort.Direction.DESC, "priority")));
    @Autowired
    private BatchJobRepository batchJobRepository;
    @Test
    public void findById(){
        DossierBatchJob result = batchJobRepository.findById(1);
        DossierBatchJobParameter parameter1= new DossierBatchJobParameter();
        DossierBatchJobParameter parameter2= new DossierBatchJobParameter();

        parameter1.setId(1);
        parameter1.setParameterKey("IRN");
        parameter1.setParameterValue("123456789");
        parameter2.setId(2);
        parameter2.setParameterKey("OWNER");
        parameter2.setParameterValue("psob admin");

        DossierBatchJob expected= new DossierBatchJob();
        expected.setId(1);
        expected.setUuid(UUID1);
        expected.setType(BTCHANNSTATPROTO);
        expected.setPriority(FIRST_PRIORITY);
        expected.setCreateTime(result.getCreateTime());//fake the createTime since we are using CURRENT_TIMESTAMP in the query
        expected.setDescription(A_SIMPLE_PSOB_TASK);
        expected.setStatus(DossierBatchJobStatus.NEW);
        expected.setResult("");
        List<DossierBatchJobParameter> b = new ArrayList<DossierBatchJobParameter>();
        b.add(parameter1);
        b.add(parameter2);
        expected.setParameters(b);
        assertThat(result).isEqualTo(expected);

    }

    @Test
    public void findByStatus_(){
        List<DossierBatchJob> result = batchJobRepository.findByStatus(DossierBatchJobStatus.PND,pageable);
        assertThat(result.size()).isEqualTo(5);
    }

    @Test
    public void findByType(){
        List<DossierBatchJob> result = batchJobRepository.findByType(BTCHANNSTATPROTO,pageable);
        assertThat(result.size()).isEqualTo(6);
    }

    @Test
    public void findByTypeAndStatus(){
        List<DossierBatchJob> result = batchJobRepository.findByTypeAndStatus(BTCHANNSTATPROTO,DossierBatchJobStatus.PND,pageable);
        assertThat(result.size()).isEqualTo(4);
    }
}
