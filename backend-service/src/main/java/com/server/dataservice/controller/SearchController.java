package com.server.dataservice.controller;

import com.server.dataservice.service.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("search")
public class SearchController
{
    @Autowired
    private SearchRepository searchService;

    @GetMapping("/{text}")
    public ResponseEntity<Map<String, List<Object>>> search(@PathVariable("text") String text) {
        return new ResponseEntity<>(searchService.search(text), HttpStatus.OK);
    }
}
