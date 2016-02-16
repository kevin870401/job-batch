package com.otpp.jobBatch.data.repository;

import com.otpp.jobBatch.data.entity.BatchJobLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchJobLogRepository extends JpaRepository<BatchJobLog, Long> {

    List<BatchJobLog> findByJobId(@Param("jobId") long jobId, Pageable pageRequest);

}
