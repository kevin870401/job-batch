package com.otpp.jobBatch.data.repository;

import com.otpp.jobBatch.data.entity.BatchJob;
import com.otpp.jobBatch.data.entity.BatchJobParameter;
import com.otpp.jobBatch.data.entity.enums.DossierBatchJobStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class BatchJobRepositoryIT extends ITTestBase {
    private static final UUID UUID1 = UUID.fromString("07ac789c-d19c-11e5-ab30-625662870761") ;
    private static final UUID UUIDNEW = UUID.fromString("07ac8436-d19c-11e5-ab30-625662870761") ;
    private static final String BTCHANNSTATPROTO = "BTCHANNSTATPROTO";
    private static final short FIRST_PRIORITY = 1;
    private static final String A_SIMPLE_PSOB_TASK = "a simple psob task";
    private static final String IRN = "IRN";
    private static final String OWNER = "OWNER";
    private Pageable pageable= new PageRequest(0,10,new Sort(new Sort.Order(Sort.Direction.DESC, "priority")));
    @Autowired
    private BatchJobRepository batchJobRepository;
    @Test
    public void findById_IDOne_success(){
        BatchJob result = batchJobRepository.findById(1);
        BatchJobParameter parameter1= new BatchJobParameter();
        BatchJobParameter parameter2= new BatchJobParameter();

        parameter1.setId(1);
        parameter1.setParameterKey(IRN);
        parameter1.setParameterValue("123456789");
        parameter2.setId(2);
        parameter2.setParameterKey(OWNER);
        parameter2.setParameterValue("psob admin");

        BatchJob expected= new BatchJob();
        expected.setId(1);
        expected.setUuid(UUID1);
        expected.setType(BTCHANNSTATPROTO);
        expected.setPriority(FIRST_PRIORITY);
        expected.setCreateTime(result.getCreateTime());//fake the createTime since we are using CURRENT_TIMESTAMP in the query
        expected.setDescription(A_SIMPLE_PSOB_TASK);
        expected.setStatus(DossierBatchJobStatus.NEW);
        expected.setResult("");
        List<BatchJobParameter> parameters = new ArrayList<BatchJobParameter>();
        parameters.add(parameter1);
        parameters.add(parameter2);
        expected.setParameters(parameters);
        assertThat(result).isEqualTo(expected);

    }

    @Test
    public void findByStatus_statusPending_fiveFound(){
        List<BatchJob> result = batchJobRepository.findByStatus(DossierBatchJobStatus.PND,pageable);
        assertThat(result.size()).isEqualTo(5);
    }

    @Test
    public void findByType_typeBTCHANNSTATPROTO_sixFound(){
        List<BatchJob> result = batchJobRepository.findByType(BTCHANNSTATPROTO,pageable);
        assertThat(result.size()).isEqualTo(6);
    }

    @Test
    public void findByTypeAndStatus_typeBTCHANNSTATPROTOAndStatusPending_fourFound(){
        List<BatchJob> result = batchJobRepository.findByTypeAndStatus(BTCHANNSTATPROTO,DossierBatchJobStatus.PND,pageable);
        assertThat(result.size()).isEqualTo(4);
    }

    @Test
    public void saveAndFlush_persistNew_Success(){

        BatchJobParameter parameter1= new BatchJobParameter();
        BatchJobParameter parameter2= new BatchJobParameter();

        parameter1.setParameterKey(IRN);
        parameter1.setParameterValue("123456789");

        parameter2.setParameterKey(OWNER);
        parameter2.setParameterValue("psob admin");

        BatchJob expected= new BatchJob();
        //expected.setId(10);
        expected.setUuid(UUIDNEW);
        expected.setType(BTCHANNSTATPROTO);
        expected.setPriority(FIRST_PRIORITY);
        expected.setDescription(A_SIMPLE_PSOB_TASK);
        expected.setStatus(DossierBatchJobStatus.PND);
        expected.setResult("");
        List<BatchJobParameter> parameters = new ArrayList<BatchJobParameter>();
        parameter1.setJob(expected);
        parameter2.setJob(expected);
        parameters.add(parameter1);
        parameters.add(parameter2);
        expected.setParameters(parameters);


        BatchJob result= batchJobRepository.saveAndFlush(expected);
        assertThat(result.getId()).isNotNull();
        assertThat(result).isEqualTo(expected);

    }

    @Test
    public void saveAndFlush_updateOnlyStatus_sucess(){

        BatchJob testCase = batchJobRepository.findById(1);
        assertThat(testCase.getStatus()).isEqualTo(DossierBatchJobStatus.NEW);
        testCase.setStatus(DossierBatchJobStatus.CPT);
        BatchJob result= batchJobRepository.saveAndFlush(testCase);
        assertThat(result.getStatus()).isEqualTo(DossierBatchJobStatus.CPT);
    }
    @Rollback(false)
    @Transactional
    @Test
    public void updateJobStatus_validJobStatus_sucess(){

        batchJobRepository.updateJobStatus(1,DossierBatchJobStatus.PND);

        assertThat(100).isEqualTo(1);
    }


}
