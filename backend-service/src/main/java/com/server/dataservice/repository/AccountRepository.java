package com.server.dataservice.repository;

import com.server.common.model.Account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByDeletedFalse();
}
