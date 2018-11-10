package com.server.dataservice.controller;

import com.google.gson.Gson;
import com.server.common.model.File;
import com.server.dataservice.service.FileRepositoryService;
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
@RequestMapping("files")
public class FileController
{
    @Autowired
    private FileRepositoryService fileService;

    @GetMapping("/get-all")
    public ResponseEntity<List<File>> getAll() {
        return new ResponseEntity<>(fileService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<File> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(fileService.get(id), HttpStatus.OK);
    }

    @GetMapping("/get-by-external-reference/{externalReference}")
    public ResponseEntity<File> get(@PathVariable("externalReference") String externalReference) {
        return new ResponseEntity<>(fileService.get(externalReference), HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public void create(@RequestBody String payload) {
        fileService.create(new Gson().fromJson(payload, File.class));
    }

    @PostMapping(value = "/delete", consumes = "application/json")
    public void delete(@RequestBody String payload) {
        long id = Long.valueOf(payload);
        fileService.delete(id);
    }
}
