package com.server.frontendservice.service;

import com.server.common.model.ApplicationError;
import com.server.frontendservice.repository.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ErrorService extends BaseService
{
    @Autowired
    private ErrorRepository errorRepository;

    public List<ApplicationError> getAll() {

        return errorRepository.getAll();
    }

    public ApplicationError getById(long id) {

        return errorRepository.getById(id);
    }

    public void update(ApplicationError error) {
        errorRepository.create(error);
    }
}
