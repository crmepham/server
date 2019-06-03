package com.server.dataservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.server.common.model.File;

@Repository
@Transactional
public class FileRepositoryImpl implements FileRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<File> findByPropertyValue(String name, String value, String threshold)  {
        Query query = entityManager.createNativeQuery("SELECT f.* " +
                                                               "FROM file as f " +
                                                               "JOIN file_property fp on fp.file_id = f.id " +
                                                               "WHERE fp.name = ? " +
                                                               "AND fp.value = ? " +
                                                               "AND f.created < now() - interval ? day", File.class);
        query.setParameter(1, name);
        query.setParameter(2, value);
        query.setParameter(3, threshold);
        return query.getResultList();
    }
}
