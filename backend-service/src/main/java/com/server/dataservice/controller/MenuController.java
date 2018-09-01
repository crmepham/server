package com.server.dataservice.controller;

import com.server.common.model.Menu;
import com.server.dataservice.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menus")
public class MenuController
{
    @Autowired
    private MenuService menuService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Menu>> getAll() {
        return new ResponseEntity<>(menuService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-all-top-level")
    public ResponseEntity<List<Menu>> getAllTopLevel() {
        return new ResponseEntity<>(menuService.getAllTopLevel(), HttpStatus.OK);
    }
}
