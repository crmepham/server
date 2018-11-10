package com.server.dataservice.controller;

import com.google.gson.Gson;
import com.server.common.model.Property;
import com.server.dataservice.service.PropertyRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("properties")
public class PropertyController
{
    @Autowired
    private PropertyRepositoryService propertyService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Property>> getAll() {
        return new ResponseEntity<>(propertyService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-reference/{reference}")
    public ResponseEntity<Property> get(@PathVariable("reference") String externalReference) {
        return new ResponseEntity<>(propertyService.get(externalReference), HttpStatus.OK);
    }

    @PostMapping(value = "/update", consumes = "application/json")
    public void update(@RequestBody String payload) {
        propertyService.update(new Gson().fromJson(payload, Property.class));
    }
}
