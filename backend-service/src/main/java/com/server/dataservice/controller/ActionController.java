package com.server.dataservice.controller;

import com.server.common.model.Action;
import com.server.dataservice.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("actions")
public class ActionController
{
    @Autowired
    private ActionService actionService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Action>> getAll() {
        return new ResponseEntity<>(actionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Action> get(@PathVariable("id") long id) {
        return new ResponseEntity<>(actionService.get(id), HttpStatus.OK);
    }
}
