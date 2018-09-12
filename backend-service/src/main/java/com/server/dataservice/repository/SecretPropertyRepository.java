package com.server.dataservice.repository;

import com.server.common.model.SecretProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface SecretPropertyRepository extends JpaRepository<SecretProperty, Long> {

    List<SecretProperty> findBySecretIdAndDeletedFalse(long id);
}
