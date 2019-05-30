package com.server.frontendservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.common.model.Email;
import com.server.common.service.BaseService;
import com.server.frontendservice.repository.EmailRepository;

@Transactional
@Service
public class EmailService extends BaseService {
    @Autowired
    private EmailRepository emailRepository;

    public List<Email> getAll() {
        return emailRepository.getAll();
    }

    public Email getById(long id) {
        return emailRepository.getById(id);
    }
}
