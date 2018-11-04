package com.server.dataservice.repository;

import com.server.common.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findByDeletedFalse();
    File findByExternalReference(String externalReference);
}
