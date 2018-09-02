package com.server.frontendservice.config;

import com.server.common.model.Menu;
import com.server.frontendservice.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Component
public class CustomAuthenticationSuccessHandler extends
        SimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{
    @Autowired
    private MenuService menuService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        // Load all menus into session
        HttpSession session = request.getSession();
        List<Menu> allTopLevel = menuService.getAllTopLevel();
        session.setAttribute("topLevelMenus", allTopLevel);
        setUseReferer(true);
        response.sendRedirect("/home");
    }
}
