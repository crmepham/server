package com.server.frontendservice.service;

import com.server.common.model.Action;
import com.server.common.model.Email;
import com.server.common.service.BaseService;
import com.server.frontendservice.repository.ActionRepository;
import com.server.frontendservice.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ActionService extends BaseService
{
    @Autowired
    private ActionRepository actionRepository;

    public List<Action> getAll() {

        return actionRepository.getAll();
    }

    public Action getById(long id) {

        return actionRepository.getById(id);
    }
}
