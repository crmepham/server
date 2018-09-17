package com.server.dataservice.controller;

import com.server.common.model.Email;
import com.server.common.model.Property;
import com.server.dataservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("emails")
public class EmailController
{
    @Autowired
    private EmailService emailService;

    @GetMapping("/send")
    public void send() {
        emailService.send();
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Email>> getAll() {
        return new ResponseEntity<>(emailService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Email> get(@PathVariable("id") long id) {
        return new ResponseEntity<>(emailService.get(id), HttpStatus.OK);
    }
}
