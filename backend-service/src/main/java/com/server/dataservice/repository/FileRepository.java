package com.server.dataservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.server.common.model.File;

@Transactional
@Repository
public interface FileRepository extends PagingAndSortingRepository<File, Long>, FileRepositoryCustom {

    List<File> findByDeletedFalseOrderByCreatedDesc();
    List<File> findByTypeAndDeletedFalseAndAbsolutePathNotNullOrderByCreatedDesc(String type, Pageable pageable);
    List<File> findByTypeAndDeletedFalseOrderByCreatedDesc(String type);
    File findByExternalReference(String externalReference);
    File findByShortReference(String shortReference);
}
