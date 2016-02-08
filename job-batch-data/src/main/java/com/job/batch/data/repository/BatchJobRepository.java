package com.job.batch.data.repository;

import com.job.batch.data.entity.DossierBatchJob;
import com.job.batch.data.entity.enums.DossierBatchJobStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchJobRepository extends JpaRepository<DossierBatchJob, Long> {

    DossierBatchJob findById(@Param("id") long id);

    List<DossierBatchJob> findByStatus(@Param("status") DossierBatchJobStatus status, Pageable pageRequest);

    List<DossierBatchJob> findByType(@Param("type") String type, Pageable pageRequest);

    List<DossierBatchJob> findByTypeAndStatus(@Param("type") String type,@Param("status") DossierBatchJobStatus status, Pageable pageRequest);
}
