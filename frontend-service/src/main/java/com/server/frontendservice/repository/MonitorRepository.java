package com.server.frontendservice.repository;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.server.common.model.Monitor;
import com.server.common.repository.BaseRepository;
import lombok.val;

@Repository
public class MonitorRepository extends BaseRepository {
    private static final String GET_ALL = "monitor/get-all";
    private static final String GET_BY_ID = "monitor/get-by-id/";
    private static final String CREATE = "monitor/create";
    private static final String DELETE = "monitor/delete";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    public CompletableFuture<List<Monitor>> getAll() {
        val res = template.exchange(baseUri + GET_ALL, GET, getEntity(), new ParameterizedTypeReference<List<Monitor>>() {});
        return CompletableFuture.completedFuture(res.getBody());
    }

    public CompletableFuture<Monitor> getById(long id) {
        val monitor = template.exchange(baseUri + GET_BY_ID + id, GET, getEntity(), new ParameterizedTypeReference<Monitor>() {});
        return CompletableFuture.completedFuture(monitor.getBody());
    }

    public void create(Monitor monitor) {
        template.exchange(baseUri + CREATE, POST, postJson(monitor), Monitor.class);
    }

    public void delete(long id) {
        template.exchange(baseUri + DELETE, POST, postJson(id), Monitor.class);
    }
}
