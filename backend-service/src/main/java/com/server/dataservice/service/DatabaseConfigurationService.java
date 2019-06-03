package com.server.dataservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.common.model.DatabaseConfiguration;
import com.server.dataservice.repository.DatabaseBackupRepository;

@Service
public class DatabaseConfigurationService {

    @Autowired
    private DatabaseBackupRepository databaseBackupRepository;

    public List<DatabaseConfiguration> getAll() {
        return databaseBackupRepository.findByDeletedFalse();
    }

    public DatabaseConfiguration get(Long id) {
        return databaseBackupRepository.findById(id).get();
    }

    public void update(DatabaseConfiguration configuration) {
        databaseBackupRepository.save(configuration);
    }

    public void delete(long id) {
        databaseBackupRepository.deleteById(id);
    }
}
