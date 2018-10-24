package com.server.dataservice.repository;

import com.server.common.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByDeletedFalse();
}
