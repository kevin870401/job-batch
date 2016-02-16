package com.otpp.batchJob.service.query.impl;

import com.otpp.batchJob.domain.mappers.BatchJobMapper;
import com.otpp.batchJob.domain.query.BatchJob;
import com.otpp.batchJob.service.query.BatchJobQueryService;
import com.otpp.jobBatch.data.repository.BatchJobRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.LinkedList;
import java.util.List;

public class BatchJobQueryServiceImpl implements BatchJobQueryService {

    private final BatchJobRepository batchJobRepository;
    private final BatchJobMapper batchJobMapper;

    public BatchJobQueryServiceImpl(BatchJobRepository batchJobRepository, BatchJobMapper batchJobMapper){
        this.batchJobRepository= batchJobRepository;
        this.batchJobMapper=batchJobMapper;
    }

    @Override
    public List<BatchJob> findByStatusOrType(String status, String type, int pageNum, int pageSize) {
        List<BatchJob> result = new LinkedList<>();
        Pageable pageable= new PageRequest(pageNum,pageSize,new Sort(new Sort.Order(Sort.Direction.DESC, "priority")));
        if(StringUtils.isBlank(status)&&!StringUtils.isBlank(type)){
            result = batchJobMapper.mapDbToDomain(batchJobRepository.findByType(type, pageable));
        }else if(!StringUtils.isBlank(status)&&StringUtils.isBlank(type)){

            result=batchJobMapper.mapDbToDomain(batchJobRepository.findByStatus(com.otpp.jobBatch.data.entity.enums.DossierBatchJobStatus.valueOf(status), pageable));
        }else if(!StringUtils.isBlank(status)&&!StringUtils.isBlank(type)){

            result=batchJobMapper.mapDbToDomain(batchJobRepository.findByTypeAndStatus(type, com.otpp.jobBatch.data.entity.enums.DossierBatchJobStatus.valueOf(status), pageable));
        }


        return result;
    }

    @Override
    public BatchJob findById(long id) {
        return batchJobMapper.mapDbToDomain(batchJobRepository.findById(id));
    }

    @Override
    public Long countByStatusOrType(String status, String type) {
        Long result;
        if(StringUtils.isBlank(status)&&!StringUtils.isBlank(type)){
            result = batchJobRepository.countByType(type );
        }else if(!StringUtils.isBlank(status)&&StringUtils.isBlank(type)){

            result=batchJobRepository.countByStatus(com.otpp.jobBatch.data.entity.enums.DossierBatchJobStatus.valueOf(status));
        }else if(!StringUtils.isBlank(status)&&!StringUtils.isBlank(type)){

            result=batchJobRepository.countByTypeAndStatus(type, com.otpp.jobBatch.data.entity.enums.DossierBatchJobStatus.valueOf(status));
        }else{
            result=batchJobRepository.count();
        }

        return result;
    }

}
