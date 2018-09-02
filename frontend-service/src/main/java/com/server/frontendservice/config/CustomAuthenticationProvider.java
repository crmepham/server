package com.server.frontendservice.config;

import com.server.frontendservice.model.User;
import com.server.frontendservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider
{

    @Autowired
    private LoginService loginService;

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException
    {

        String username = auth.getName();
        String password = (String) auth.getCredentials();

        User user = loginService.authenticate(username, password);

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

}
