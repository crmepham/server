package com.server.frontendservice.config;

import com.server.common.model.Dashboard;
import com.server.common.model.Menu;
import com.server.frontendservice.service.DashboardService;
import com.server.frontendservice.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{
    private static final Logger log = Logger.getLogger(CustomAuthenticationSuccessHandler.class.getName());

    @Autowired
    private MenuService menuService;

    @Autowired
    private DashboardService dashboardService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException
    {

        try {

            CompletableFuture<List<Menu>> menus = menuService.getAllTopLevel();
            CompletableFuture<List<Dashboard>> dashboards = dashboardService.getAll();

            CompletableFuture.allOf(menus, dashboards).join();

            HttpSession session = request.getSession();
            session.setAttribute("topLevelMenus", menus.get());
            session.setAttribute("session_dashboards", dashboards.get());

            response.sendRedirect("/home");

        } catch (Exception e) {

            final String message = "Unable to initialize user. Please contact an administrator.";
            log.severe(message);
            response.sendRedirect("/login?error" + message);
        }
    }
}
