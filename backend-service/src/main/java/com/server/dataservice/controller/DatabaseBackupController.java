package com.server.dataservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.server.common.model.DatabaseConfiguration;
import com.server.dataservice.service.DatabaseConfigurationService;

@RestController
@RequestMapping("database-configuration")
public class DatabaseBackupController {

    @Autowired
    private DatabaseConfigurationService databaseConfigurationService;

    @GetMapping("/get-all")
    public ResponseEntity<List<DatabaseConfiguration>> getAll() {
        return new ResponseEntity<>(databaseConfigurationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<DatabaseConfiguration> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(databaseConfigurationService.get(id), HttpStatus.OK);
    }

    @PostMapping(value = "/update", consumes = "application/json")
    public void update(@RequestBody String payload) {
        databaseConfigurationService.update(new Gson().fromJson(payload, DatabaseConfiguration.class));
    }

    @PostMapping(value = "/delete", consumes = "application/json")
    public void delete(@RequestBody String payload) {
        long id = Long.valueOf(payload);
        databaseConfigurationService.delete(id);
    }
}
