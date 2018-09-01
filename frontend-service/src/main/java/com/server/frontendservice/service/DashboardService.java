package com.server.frontendservice.service;

import com.server.common.model.Dashboard;
import com.server.frontendservice.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService
{
    @Autowired
    private DashboardRepository dashboardRepository;

    public List<Dashboard> getAll() {
        return dashboardRepository.getAll();
    }

    public Dashboard get(Long id) {
        return dashboardRepository.get(id);
    }

    public void update(Dashboard dashboard) {
        dashboardRepository.update(dashboard);
    }
}
