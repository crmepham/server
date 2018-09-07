package com.server.dataservice.controller;

import com.google.gson.Gson;
import com.server.common.model.Dashboard;
import com.server.common.model.Fragment;
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
@RequestMapping("fragments")
public class FragmentController
{
    @Autowired
    private FragmentService fragmentService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Fragment>> getAll() {
        return new ResponseEntity<>(fragmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-all-by-uri/{uri}")
    public ResponseEntity<List<Fragment>> getAllByUri(@PathVariable("uri") final String uri) {
        return new ResponseEntity<>(fragmentService.getAllByUri("/" + uri), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Fragment> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(fragmentService.get(id), HttpStatus.OK);
    }

    @PostMapping(value = "/update", consumes = "application/json")
    public void update(@RequestBody String payload) {
        fragmentService.update(new Gson().fromJson(payload, Fragment.class));
    }
}
