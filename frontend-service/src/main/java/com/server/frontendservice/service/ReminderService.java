package com.server.frontendservice.service;

import com.server.common.model.Reminder;
import com.server.common.model.Secret;
import com.server.common.service.BaseService;
import com.server.frontendservice.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Transactional
@Service
public class ReminderService extends BaseService
{
    @Autowired
    private ReminderRepository reminderRepository;

    public CompletableFuture<List<Reminder>> getAll() {

        return reminderRepository.getAll();
    }

    public void update(Reminder reminder) {
        reminderRepository.create(reminder);
    }

    public void delete(long id) {
        reminderRepository.delete(id);
    }

    public CompletableFuture<Reminder> getById(long id) {

        return reminderRepository.getById(id);
    }
}
