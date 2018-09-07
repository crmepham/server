package com.server.dataservice.repository;

import com.server.common.model.Fragment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface FragmentRepository extends JpaRepository<Fragment, Long> {

    List<Fragment> findByDeletedFalse();

    List<Fragment> findByUriAndDeletedFalse(String uri);
}
