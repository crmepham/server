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

import com.server.common.model.DatabaseConfiguration;
import com.server.common.repository.BaseRepository;
import lombok.val;

@Repository
public class DatabaseConfigurationRepository extends BaseRepository {
    private static final String GET_ALL = "database-configuration/get-all";
    private static final String GET_BY_ID = "database-configuration/get-by-id/";
    private static final String UPDATE = "database-configuration/update";
    private static final String DELETE = "database-configuration/delete";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    public CompletableFuture<List<DatabaseConfiguration>> getAll() {
        val res = template.exchange(baseUri + GET_ALL, GET, getEntity(), new ParameterizedTypeReference<List<DatabaseConfiguration>>() {});
        return CompletableFuture.completedFuture(res.getBody());
    }

    public DatabaseConfiguration get(final Long id) {
        val res = template.exchange(baseUri + GET_BY_ID + id, GET, getEntity(), DatabaseConfiguration.class);
        return res.getBody();
    }

    public void update(DatabaseConfiguration databaseConfiguration) {
        template.exchange(baseUri + UPDATE, POST, postJson(databaseConfiguration), DatabaseConfiguration.class);
    }

    public void delete(long id) {
        template.exchange(baseUri + DELETE, POST, postJson(id), DatabaseConfiguration.class);
    }
}
