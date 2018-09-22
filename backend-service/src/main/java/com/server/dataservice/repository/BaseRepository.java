package com.server.dataservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseRepository {

    @Autowired
    protected JdbcTemplate jdbcTemplate;
}
