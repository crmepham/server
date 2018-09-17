package com.server.dataservice.service;

import com.server.common.model.Reminder;
import com.server.common.model.Secret;
import com.server.common.model.SecretProperty;
import com.server.dataservice.repository.ReminderRepository;
import com.server.dataservice.repository.SecretPropertyRepository;
import com.server.dataservice.repository.SecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderService
{
    @Autowired
    private ReminderRepository reminderRepository;

    public List<Reminder> getAll() {
        return reminderRepository.findByDeletedFalse();
    }

    public Reminder get(Long id) {
        return reminderRepository.findById(id).get();
    }

    public void create(Reminder reminder) {
        reminderRepository.save(reminder);
    }

    public void delete(long id) {
        reminderRepository.deleteById(id);
    }
}
