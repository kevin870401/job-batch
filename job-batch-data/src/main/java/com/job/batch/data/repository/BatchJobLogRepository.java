package com.job.batch.data.repository;

import com.job.batch.data.entity.DossierBatchJobLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchJobLogRepository extends JpaRepository<DossierBatchJobLog, Long> {

    List<DossierBatchJobLog> findByJobId(@Param("jobId") long jobId, Pageable pageRequest);

}
