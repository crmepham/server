package com.server.dataservice.repository;

import com.server.common.model.AccountTransaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {

    AccountTransaction findFirstByNameAndDeletedFalse(String name);
}
