package com.server.frontendservice.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.common.model.Monitor;
import com.server.common.service.BaseService;
import com.server.frontendservice.repository.MonitorRepository;

@Transactional
@Service
public class MonitorService extends BaseService {
    @Autowired
    private MonitorRepository monitorRepository;

    public CompletableFuture<List<Monitor>> getAll() {
        return monitorRepository.getAll();
    }

    public void update(Monitor monitor) {
        monitorRepository.create(monitor);
    }

    public void delete(long id) {
        monitorRepository.delete(id);
    }

    public CompletableFuture<Monitor> getById(long id) {
        return monitorRepository.getById(id);
    }
}
