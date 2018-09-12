package com.server.dataservice.repository;

import com.server.common.model.Secret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface SecretRepository extends JpaRepository<Secret, Long> {

    List<Secret> findByDeletedFalse();
}
