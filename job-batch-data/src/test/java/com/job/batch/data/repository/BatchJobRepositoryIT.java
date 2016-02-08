package com.job.batch.data.repository;

import com.job.batch.data.ITContext;
import com.job.batch.data.entity.DossierBatchJob;
import com.job.batch.data.entity.DossierBatchJobLog;
import com.job.batch.data.entity.DossierBatchJobParameter;
import com.job.batch.data.entity.enums.DossierBatchJobStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class BatchJobRepositoryIT extends ITTestBase {
    private static final String UUID1 = "10000001";
    private static final String UUIDNEW = "11110001";
    private static final String BTCHANNSTATPROTO = "BTCHANNSTATPROTO";
    private static final String FIRST_PRIORITY = "1";
    private static final String A_SIMPLE_PSOB_TASK = "a simple psob task";
    private static final String IRN = "IRN";
    private static final String OWNER = "OWNER";
    private Pageable pageable= new PageRequest(0,10,new Sort(new Sort.Order(Sort.Direction.DESC, "priority")));
    @Autowired
    private BatchJobRepository batchJobRepository;
    @Test
    public void findById_IDOne_success(){
        DossierBatchJob result = batchJobRepository.findById(1);
        DossierBatchJobParameter parameter1= new DossierBatchJobParameter();
        DossierBatchJobParameter parameter2= new DossierBatchJobParameter();

        parameter1.setId(1);
        parameter1.setParameterKey(IRN);
        parameter1.setParameterValue("123456789");
        parameter2.setId(2);
        parameter2.setParameterKey(OWNER);
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
        List<DossierBatchJobParameter> parameters = new ArrayList<DossierBatchJobParameter>();
        parameters.add(parameter1);
        parameters.add(parameter2);
        expected.setParameters(parameters);
        assertThat(result).isEqualTo(expected);

    }

    @Test
    public void findByStatus_statusPending_fiveFound(){
        List<DossierBatchJob> result = batchJobRepository.findByStatus(DossierBatchJobStatus.PND,pageable);
        assertThat(result.size()).isEqualTo(5);
    }

    @Test
    public void findByType_typeBTCHANNSTATPROTO_sixFound(){
        List<DossierBatchJob> result = batchJobRepository.findByType(BTCHANNSTATPROTO,pageable);
        assertThat(result.size()).isEqualTo(6);
    }

    @Test
    public void findByTypeAndStatus_typeBTCHANNSTATPROTOAndStatusPending_fourFound(){
        List<DossierBatchJob> result = batchJobRepository.findByTypeAndStatus(BTCHANNSTATPROTO,DossierBatchJobStatus.PND,pageable);
        assertThat(result.size()).isEqualTo(4);
    }

    @Test
    public void saveAndFlush_persistNew_Success(){

        DossierBatchJobParameter parameter1= new DossierBatchJobParameter();
        DossierBatchJobParameter parameter2= new DossierBatchJobParameter();

        parameter1.setParameterKey(IRN);
        parameter1.setParameterValue("123456789");

        parameter2.setParameterKey(OWNER);
        parameter2.setParameterValue("psob admin");

        DossierBatchJob expected= new DossierBatchJob();
        //expected.setId(10);
        expected.setUuid(UUIDNEW);
        expected.setType(BTCHANNSTATPROTO);
        expected.setPriority(FIRST_PRIORITY);
        expected.setDescription(A_SIMPLE_PSOB_TASK);
        expected.setStatus(DossierBatchJobStatus.PND);
        expected.setResult("");
        List<DossierBatchJobParameter> parameters = new ArrayList<DossierBatchJobParameter>();
        parameter1.setJob(expected);
        parameter2.setJob(expected);
        parameters.add(parameter1);
        parameters.add(parameter2);
        expected.setParameters(parameters);


        DossierBatchJob result= batchJobRepository.saveAndFlush(expected);
        assertThat(result.getId()).isNotNull();
        assertThat(result).isEqualTo(expected);

    }

    @Test
    public void saveAndFlush_updateOnlyStatus_sucess(){

        DossierBatchJob testCase = batchJobRepository.findById(1);
        assertThat(testCase.getStatus()).isEqualTo(DossierBatchJobStatus.NEW);
        testCase.setStatus(DossierBatchJobStatus.CPT);
        DossierBatchJob result= batchJobRepository.saveAndFlush(testCase);
        assertThat(result.getStatus()).isEqualTo(DossierBatchJobStatus.CPT);
    }



}
