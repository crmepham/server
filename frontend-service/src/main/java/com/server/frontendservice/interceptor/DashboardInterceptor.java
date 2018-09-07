package com.server.frontendservice.interceptor;

import com.server.common.model.Dashboard;
import com.server.common.model.Fragment;
import com.server.frontendservice.service.FragmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;

@Component
public class DashboardInterceptor implements HandlerInterceptor
{
    @Autowired
    private FragmentService fragmentService;

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView)
    {
        final String requestURI = request.getRequestURI();

        HttpSession session = request.getSession();
        List<Dashboard> dashboards = (List<Dashboard>) session.getAttribute("session_dashboards");

        if (modelAndView != null && dashboards != null && !dashboards.isEmpty()) {

            Optional<Dashboard> optional = dashboards.stream()
                                            .filter(d -> d.getEnabled() && d.getUri().equals(requestURI))
                                            .findFirst();
            if (optional.isPresent()) {

                Dashboard dashboard = optional.get();

                Map<String, Fragment> fragments = null;
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
