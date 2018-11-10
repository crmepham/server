package com.server.common.repository;

import com.server.common.model.ApplicationError;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Repository
public class ErrorRepository extends BaseRepository
{
    private static final String GET_ALL = "errors/get-all";
    private static final String GET_BY_ID = "errors/get-by-id/";
    private static final String CREATE = "errors/create";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    public List<ApplicationError> getAll() {

        ResponseEntity<List<ApplicationError>> res = template.exchange(baseUri + GET_ALL, GET, getEntity(), new ParameterizedTypeReference<List<ApplicationError>>() {});
        return res.getBody();
    }

    public ApplicationError getById(long id) {

        ResponseEntity<ApplicationError> error = template.exchange(baseUri + GET_BY_ID + id, GET, getEntity(), new ParameterizedTypeReference<ApplicationError>() {});
        return error.getBody();
    }

    public void create(ApplicationError error) {

        template.exchange(baseUri + CREATE, POST, postJson(error), ApplicationError.class);
    }
}
