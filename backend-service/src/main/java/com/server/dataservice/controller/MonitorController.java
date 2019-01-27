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
import com.server.common.model.Monitor;
import com.server.dataservice.service.MonitorService;

@RestController
@RequestMapping("monitor")
public class MonitorController
{
    @Autowired
    private MonitorService monitorService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Monitor>> getAll() {
        return new ResponseEntity<>(monitorService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Monitor> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(monitorService.get(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public void create(@RequestBody String payload) {
        monitorService.create(new Gson().fromJson(payload, Monitor.class));
    }

    @PostMapping(value = "/delete", consumes = "application/json")
    public void delete(@RequestBody String payload) {
        long id = Long.valueOf(payload);
        monitorService.delete(id);
    }
}
