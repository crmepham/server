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

import com.server.common.model.Secret;
import com.server.common.model.SecretProperty;
import com.server.common.repository.BaseRepository;
import lombok.val;

@Repository
public class SecretRepository extends BaseRepository {
    private static final String GET_ALL = "secrets/get-all";
    private static final String GET_BY_ID = "secrets/get-by-id/";
    private static final String CREATE = "secrets/create";
    private static final String DELETE = "secrets/delete";
    private static final String CREATE_PROPERTY = "secrets/create-property";
    private static final String DELETE_PROPERTY = "secrets/delete-property/";
    private static final String GET_PROPERTIES = "secrets/get-properties-by-secret-id/";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    public CompletableFuture<List<Secret>> getAll() {
        val res = template.exchange(baseUri + GET_ALL, GET, getEntity(), new ParameterizedTypeReference<List<Secret>>() {});
        return CompletableFuture.completedFuture(res.getBody());
    }

    public CompletableFuture<Secret> getById(long id) {
        val error = template.exchange(baseUri + GET_BY_ID + id, GET, getEntity(), new ParameterizedTypeReference<Secret>() {});
        return CompletableFuture.completedFuture(error.getBody());
    }

    public void create(Secret secret) {
        template.exchange(baseUri + CREATE, POST, postJson(secret), Secret.class);
    }

    public void createProperty(SecretProperty secretProperty) {
        template.exchange(baseUri + CREATE_PROPERTY, POST, postJson(secretProperty), SecretProperty.class);
    }

    public void deleteProperty(long id) {
        template.exchange(baseUri + DELETE_PROPERTY + id, POST, postJson(null), SecretProperty.class);
    }

    public CompletableFuture<List<SecretProperty>> getProperties(long id) {
        val res = template.exchange(baseUri + GET_PROPERTIES + id, GET, getEntity(), new ParameterizedTypeReference<List<SecretProperty>>() {});
        return CompletableFuture.completedFuture(res.getBody());
    }

    public void delete(long id) {
        template.exchange(baseUri + DELETE, POST, postJson(id), Secret.class);
    }
}
