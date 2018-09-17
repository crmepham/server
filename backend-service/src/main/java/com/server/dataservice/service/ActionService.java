package com.server.dataservice.service;

import com.server.common.model.Action;
import com.server.dataservice.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionService
{
    @Autowired
    private ActionRepository actionRepository;

    public Action get(long id) {
        return actionRepository.getOne(id);
    }

    public List<Action> getAll() {
        return actionRepository.findAllByDeletedFalseOrderByLastUpdatedDesc();
    }
}
