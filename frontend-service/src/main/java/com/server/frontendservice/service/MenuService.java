package com.server.frontendservice.service;

import com.server.common.model.Menu;
import com.server.frontendservice.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService
{
    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllTopLevel() {
        return menuRepository.getAllTopLevel();
    }
}
