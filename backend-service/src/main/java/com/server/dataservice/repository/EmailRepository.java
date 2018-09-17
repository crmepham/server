package com.server.dataservice.repository;

import com.server.common.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    List<Email> findBySentFalseAndRetryCountLessThanAndDeletedFalse(int retryThreshold);
}
