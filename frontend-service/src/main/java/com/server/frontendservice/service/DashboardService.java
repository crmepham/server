package com.server.frontendservice.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.common.model.Dashboard;
import com.server.frontendservice.repository.DashboardRepository;
import lombok.val;

@Transactional
@Service
public class DashboardService
{
    @Autowired
    private DashboardRepository dashboardRepository;

    @Async("asyncExecutor")
    public CompletableFuture<List<Dashboard>> getAll() {
        return dashboardRepository.getAll();
    }

    public Dashboard get(Long id) {
        return dashboardRepository.get(id);
    }

    public void update(Dashboard dashboard, HttpServletRequest request) {
        dashboardRepository.update(dashboard);
        val session = request.getSession();
        val dashboards = (List<Dashboard>) session.getAttribute("session_dashboards");
        dashboards.removeIf(d -> d.getUri().equals(dashboard.getUri()));
        dashboards.add(dashboard);
    }
}
