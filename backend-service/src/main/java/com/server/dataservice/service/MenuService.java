package com.server.dataservice.service;

import com.server.common.model.Menu;
import com.server.dataservice.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService
{
    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    public List<Menu> getAllTopLevel() {
        return menuRepository.findByParentNullAndDeletedFalseOrderByItemOrder();
    }
}
