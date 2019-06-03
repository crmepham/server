package com.server.dataservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.server.common.model.File;

@Transactional
@Repository
public interface FileRepository extends JpaRepository<File, Long>, FileRepositoryCustom {

    List<File> findByDeletedFalseOrderByCreatedDesc();
    List<File> findByTypeAndDeletedFalseOrderByCreatedDesc(String type);
    File findByExternalReference(String externalReference);
    File findByShortReference(String shortReference);
}
