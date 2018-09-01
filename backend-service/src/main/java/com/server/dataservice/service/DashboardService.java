package com.server.dataservice.service;

import com.server.common.model.Dashboard;
import com.server.dataservice.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService
{
    @Autowired
    private DashboardRepository dashboardRepository;

    public List<Dashboard> getAll() {
        return dashboardRepository.findByDeletedFalse();
    }

    public Dashboard get(Long id) {
        return dashboardRepository.findById(id).get();
    }

    public void update(Dashboard dashboard) {
        dashboardRepository.save(dashboard);
    }
}
