package com.server.frontendservice.service;

import com.server.common.model.Dashboard;
import com.server.frontendservice.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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
        HttpSession session = request.getSession();
        final List<Dashboard> dashboards = (List<Dashboard>) session.getAttribute("session_dashboards");
        dashboards.removeIf(d -> d.getUri().equals(dashboard.getUri()));
        dashboards.add(dashboard);
    }
}
