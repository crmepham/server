package com.server.frontendservice.interceptor;

import static java.lang.Boolean.parseBoolean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.server.common.service.PropertyService;

@Component
public class PropertyInterceptor implements HandlerInterceptor
{
    @Autowired
    private PropertyService propertyService;

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView)
    {
        if (modelAndView != null) {
            modelAndView.addObject("hideSensitiveData", parseBoolean(propertyService.getByExternalReference("hide_sensitive_data").getValue()));
        }
    }
}
