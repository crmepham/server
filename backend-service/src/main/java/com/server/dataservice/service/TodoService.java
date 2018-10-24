package com.server.dataservice.service;

import com.server.common.model.Todo;
import com.server.dataservice.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService
{
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAll() {
        return todoRepository.findByDeletedFalse();
    }

    public Todo get(Long id) {
        return todoRepository.findById(id).get();
    }

    public void create(Todo todo) {
        todoRepository.save(todo);
    }

    public void delete(long id) {
        todoRepository.deleteById(id);
    }
}
