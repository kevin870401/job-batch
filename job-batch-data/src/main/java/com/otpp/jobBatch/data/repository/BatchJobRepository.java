package com.otpp.jobBatch.data.repository;

import com.otpp.jobBatch.data.entity.BatchJob;
import com.otpp.jobBatch.data.entity.enums.DossierBatchJobStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BatchJobRepository extends JpaRepository<BatchJob, Long> {

    BatchJob findById(@Param("id") long id);

    List<BatchJob> findByStatus(@Param("status") DossierBatchJobStatus status, Pageable pageRequest);

    List<BatchJob> findByType(@Param("type") String type, Pageable pageRequest);

    List<BatchJob> findByTypeAndStatus(@Param("type") String type,@Param("status") DossierBatchJobStatus status, Pageable pageRequest);

    Long countByStatus(@Param("status") DossierBatchJobStatus status);

    Long countByType(@Param("type") String type);

    Long countByTypeAndStatus(@Param("type") String type,@Param("status") DossierBatchJobStatus status);

    @Transactional
    @Modifying
    @Query("UPDATE com.otpp.jobBatch.data.entity.BatchJob job SET job.status=:status WHERE job.id=:id")
    void updateJobStatus(@Param("id") long id,@Param("status") DossierBatchJobStatus status);
}
