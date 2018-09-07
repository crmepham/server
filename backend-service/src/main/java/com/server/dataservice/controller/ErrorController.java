package com.server.dataservice.controller;

import com.google.gson.Gson;
import com.server.common.model.ApplicationError;
import com.server.common.model.Fragment;
import com.server.dataservice.service.ErrorService;
import com.server.dataservice.service.FragmentService;
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
@RequestMapping("errors")
public class ErrorController
{
    @Autowired
    private ErrorService errorService;

    @GetMapping("/get-all")
    public ResponseEntity<List<ApplicationError>> getAll() {
        return new ResponseEntity<>(errorService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ApplicationError> getById(@PathVariable("id") long id) {
        return new ResponseEntity<>(errorService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public void update(@RequestBody String payload) {
        errorService.create(new Gson().fromJson(payload, ApplicationError.class));
    }
}
