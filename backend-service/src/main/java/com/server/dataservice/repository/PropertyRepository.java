package com.server.dataservice.repository;

import com.server.common.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByDeletedFalse();
    Property findByExternalReference(String externalReference);
}
