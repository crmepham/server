package com.server.dataservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.common.model.Monitor;
import com.server.dataservice.repository.MonitorRepository;

@Service
public class MonitorService
{
    @Autowired
    private MonitorRepository monitorRepository;

    public List<Monitor> getAll() {
        return monitorRepository.findByDeletedFalse();
    }

    public Monitor get(Long id) {
        return monitorRepository.findById(id).get();
    }

    public void create(Monitor monitor) {
        monitorRepository.save(monitor);
    }

    public void delete(long id) {
        monitorRepository.deleteById(id);
    }
}
