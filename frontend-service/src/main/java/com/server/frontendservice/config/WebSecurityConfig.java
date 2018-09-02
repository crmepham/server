package com.server.frontendservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers(new String[] {"*/shared/**", "*/configuration/**"}).permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .successHandler(customAuthenticationSuccessHandler)
                .loginPage("/login").permitAll()
                .and().logout()
                .logoutUrl("/logout").permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.authProvider);
    }
}
