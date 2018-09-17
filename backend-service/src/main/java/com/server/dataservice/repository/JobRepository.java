package com.server.dataservice.repository;

import com.server.common.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByScheduleIdAndDeletedFalse(long id);
}
