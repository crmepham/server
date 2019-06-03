package com.server.dataservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.server.common.model.DatabaseConfiguration;

@Transactional
@Repository
public interface DatabaseBackupRepository extends JpaRepository<DatabaseConfiguration, Long> {

    List<DatabaseConfiguration> findByDeletedFalseAndEnabledTrue();
    List<DatabaseConfiguration> findByDeletedFalse();
}
