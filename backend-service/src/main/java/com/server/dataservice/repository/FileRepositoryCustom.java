package com.server.dataservice.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.server.common.model.File;

@Transactional
@Repository
public interface FileRepositoryCustom {

    List<File> findByPropertyValue(String name, String value, String threshold);
}
