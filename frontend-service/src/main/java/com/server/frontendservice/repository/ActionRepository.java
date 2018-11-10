package com.server.frontendservice.repository;

import com.server.common.model.Action;
import com.server.common.model.Email;
import com.server.common.repository.BaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Repository
public class ActionRepository extends BaseRepository
{
    private static final String GET_ALL = "actions/get-all";
    private static final String GET_BY_ID = "actions/get-by-id/";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    public List<Action> getAll() {

        ResponseEntity<List<Action>> res = template.exchange(baseUri + GET_ALL, GET, getEntity(), new ParameterizedTypeReference<List<Action>>() {});
        return res.getBody();
    }

    public Action getById(long id) {

        ResponseEntity<Action> action = template.exchange(baseUri + GET_BY_ID + id, GET, getEntity(), new ParameterizedTypeReference<Action>() {});
        return action.getBody();
    }
}
