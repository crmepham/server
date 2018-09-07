package com.server.frontendservice.service;

import com.server.common.model.Menu;
import com.server.frontendservice.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MenuService
{
    @Autowired
    private MenuRepository menuRepository;

    @Async("asyncExecutor")
    public CompletableFuture<List<Menu>> getAllTopLevel() {
        return menuRepository.getAllTopLevel();
    }
}
