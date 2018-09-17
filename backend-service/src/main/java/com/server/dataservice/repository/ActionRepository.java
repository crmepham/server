package com.server.dataservice.repository;

import com.server.common.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

    List<Action> findByClassNameAndStateAndDeletedFalse(String className, String state);
}
