package com.server.dataservice.service;

import com.server.common.model.ApplicationError;
import com.server.dataservice.repository.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorService
{
    @Autowired
    private ErrorRepository errorRepository;

    public List<ApplicationError> getAll() {
        return errorRepository.findByDeletedFalseOrderByCreatedDesc();
    }

    public ApplicationError getById(long id) {
        return errorRepository.findByIdAndDeletedFalse(id);
    }

    public void create(ApplicationError error) {
        errorRepository.save(error);
    }
}
