package com.server.dataservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.server.common.model.Monitor;

@Transactional
@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {

    List<Monitor> findByDeletedFalse();
    Monitor findByName(String name);
}
