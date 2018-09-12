package com.server.dataservice.controller;

import com.google.gson.Gson;
import com.server.common.model.Secret;
import com.server.common.model.SecretProperty;
import com.server.dataservice.service.SecretService;
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
@RequestMapping("secrets")
public class SecretController
{
    @Autowired
    private SecretService secretService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Secret>> getAll() {
        return new ResponseEntity<>(secretService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Secret> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(secretService.get(id), HttpStatus.OK);
    }

    @PostMapping(value = "/update", consumes = "application/json")
    public void update(@RequestBody String payload) {
        secretService.update(new Gson().fromJson(payload, Secret.class));
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public void create(@RequestBody String payload) {
        secretService.create(new Gson().fromJson(payload, Secret.class));
    }

    @PostMapping(value = "/create-property", consumes = "application/json")
    public void createProperty(@RequestBody String payload) {
        secretService.createProperty(new Gson().fromJson(payload, SecretProperty.class));
    }

    @PostMapping(value = "/delete-property/{id}", consumes = "application/json")
    public void deleteProperty(@PathVariable("id") Long id) {
        secretService.deleteProperty(id);
    }

    @PostMapping(value = "/delete", consumes = "application/json")
    public void deleteProperty(@RequestBody String payload) {
        long id = Long.valueOf(payload);

        List<SecretProperty> properties = secretService.getProperties(id);
        properties.forEach(p -> secretService.deleteProperty(p.getId()));

        secretService.delete(id);
    }

    @GetMapping("/get-properties-by-secret-id/{id}")
    public ResponseEntity<List<SecretProperty>> getProperties(@PathVariable("id") Long id) {
        return new ResponseEntity<>(secretService.getProperties(id), HttpStatus.OK);
    }
}
