package com.server.frontendservice.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.common.model.Todo;
import com.server.common.service.BaseService;
import com.server.frontendservice.repository.TodoRepository;

@Transactional
@Service
public class TodoService extends BaseService {
    @Autowired
    private TodoRepository todoRepository;

    public CompletableFuture<List<Todo>> getAll() {
        return todoRepository.getAll();
    }

    public void update(Todo todo) {
        todoRepository.create(todo);
    }

    public void delete(long id) {
        todoRepository.delete(id);
    }

    public CompletableFuture<Todo> getById(long id) {
        return todoRepository.getById(id);
    }
}
