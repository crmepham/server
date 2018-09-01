package com.server.dataservice.controller;

import com.google.gson.Gson;
import com.server.common.model.Dashboard;
import com.server.dataservice.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dashboards")
public class DashboardController
{
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Dashboard>> getAll() {
        return new ResponseEntity<>(dashboardService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Dashboard> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(dashboardService.get(id), HttpStatus.OK);
    }

    @PostMapping(value = "/update", consumes = "application/json")
    public void update(@RequestBody String payload) {
        dashboardService.update(new Gson().fromJson(payload, Dashboard.class));
    }
}
