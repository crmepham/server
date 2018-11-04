package com.server.dataservice.controller;

import com.server.dataservice.service.FilePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("properties")
public class FilePropertyController
{
    @Autowired
    private FilePropertyService filePropertyService;

    @PostMapping(value = "/delete-all", consumes = "application/json")
    public void update(@RequestBody long id) {
        filePropertyService.deleteAll(id);
    }
}
