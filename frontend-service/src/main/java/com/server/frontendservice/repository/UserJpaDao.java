package com.server.frontendservice.repository;

import com.server.frontendservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserJpaDao extends JpaRepository<User, Integer> {

    User findByUsernameAndEnabledTrueAndDeletedFalse(String username);
}
