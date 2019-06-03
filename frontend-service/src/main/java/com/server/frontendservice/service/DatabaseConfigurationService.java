package com.server.frontendservice.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.common.model.DatabaseConfiguration;
import com.server.common.service.BaseService;
import com.server.frontendservice.repository.DatabaseConfigurationRepository;

@Transactional
@Service
public class DatabaseConfigurationService extends BaseService {

    @Autowired
    private DatabaseConfigurationRepository databaseConfigurationRepository;

    @Async("asyncExecutor")
    public CompletableFuture<List<DatabaseConfiguration>> getAll()
    {
        return databaseConfigurationRepository.getAll();
    }

    public DatabaseConfiguration get(Long id) {
        return databaseConfigurationRepository.get(id);
    }

    public void update(DatabaseConfiguration configuration) {
        databaseConfigurationRepository.update(configuration);
    }

    public void delete(long id) {
        databaseConfigurationRepository.delete(id);
    }
}
