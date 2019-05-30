package com.server.frontendservice.interceptor;

import static java.lang.String.format;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.server.common.model.Dashboard;
import com.server.common.model.Fragment;
import com.server.frontendservice.service.FragmentService;
import lombok.val;

@Component
public class DashboardInterceptor implements HandlerInterceptor {
    @Autowired
    private FragmentService fragmentService;

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        val requestURI = request.getRequestURI();
        val session = request.getSession();
        val dashboards = (List<Dashboard>) session.getAttribute("session_dashboards");

        if (modelAndView != null && dashboards != null && !dashboards.isEmpty()) {

            val optional = dashboards.stream()
                                     .filter(d -> d.getEnabled() && d.getUri().equals(requestURI))
                                     .findFirst();
            if (optional.isPresent()) {

                val dashboard = optional.get();
                Map<String, Fragment> fragments;
                try{
                    fragments = fragmentService.getAll(dashboard.getUri());
                } catch (Exception e) {
                    fragmentService.persistError(e, format("Failed to fetch fragments from API for uri '%s'", dashboard.getUri()));
                    fragments = Collections.emptyMap();
                }
                modelAndView.addObject("fragments", fragments);
                modelAndView.addObject("session_dashboard", dashboard);
            }
        }
    }
}
