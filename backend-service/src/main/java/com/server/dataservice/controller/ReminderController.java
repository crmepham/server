package com.server.dataservice.controller;

import com.google.gson.Gson;
import com.server.common.model.Reminder;
import com.server.dataservice.service.ReminderService;
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
@RequestMapping("reminders")
public class ReminderController
{
    @Autowired
    private ReminderService reminderService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Reminder>> getAll() {
        return new ResponseEntity<>(reminderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Reminder> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(reminderService.get(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public void create(@RequestBody String payload) {
        reminderService.create(new Gson().fromJson(payload, Reminder.class));
    }

    @PostMapping(value = "/delete", consumes = "application/json")
    public void delete(@RequestBody String payload) {
        long id = Long.valueOf(payload);
        reminderService.delete(id);
    }
}
