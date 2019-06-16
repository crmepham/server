package com.server.dataservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.server.common.model.File;

@Transactional
@Repository
public interface FileRepository extends PagingAndSortingRepository<File, Long>, FileRepositoryCustom {

    List<File> findByDeletedFalseOrderByCreatedDesc();
    List<File> findByTypeAndDeletedFalseOrderByCreatedDesc(String type);
    File findByExternalReference(String externalReference);
    File findByShortReference(String shortReference);

    @Query(value = "from File f " +
                   "where f.deleted = false " +
                   "and f.absolutePath is not null " +
                   "and year(f.created) = :year " +
                   "and f.type = :type " +
                   "order by f.created desc")
    List<File> getByYearAndType(@Param("year") Integer year,
                                @Param("type") String type,
                                Pageable pageable);

    List<File> findByDeletedFalseAndAbsolutePathNotNullAndTypeOrderByCreatedDesc(String type, Pageable pageable);
}
