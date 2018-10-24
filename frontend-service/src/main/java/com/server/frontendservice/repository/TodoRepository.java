package com.server.frontendservice.repository;

import com.server.common.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Repository
public class TodoRepository extends BaseRepository
{
    private static final String GET_ALL = "todos/get-all";
    private static final String GET_BY_ID = "todos/get-by-id/";
    private static final String CREATE = "todos/create";
    private static final String DELETE = "todos/delete";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    public CompletableFuture<List<Todo>> getAll() {

        ResponseEntity<List<Todo>> res = template.exchange(baseUri + GET_ALL, GET, getEntity(), new ParameterizedTypeReference<List<Todo>>() {});
        return CompletableFuture.completedFuture(res.getBody());
    }

    public CompletableFuture<Todo> getById(long id) {

        ResponseEntity<Todo> todo = template.exchange(baseUri + GET_BY_ID + id, GET, getEntity(), new ParameterizedTypeReference<Todo>() {});
        return CompletableFuture.completedFuture(todo.getBody());
    }

    public void create(Todo todo) {

        template.exchange(baseUri + CREATE, POST, postJson(todo), Todo.class);
    }

    public void delete(long id) {

        final String url = baseUri + DELETE;
        template.exchange(url, POST, postJson(id), Todo.class);
    }
}
