package com.server.frontendservice.repository;

import com.server.common.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Repository
public class EmailRepository extends BaseRepository
{
    private static final String GET_ALL = "emails/get-all";
    private static final String GET_BY_ID = "emails/get-by-id/";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    public List<Email> getAll() {

        ResponseEntity<List<Email>> res = template.exchange(baseUri + GET_ALL, GET, getEntity(), new ParameterizedTypeReference<List<Email>>() {});
        return res.getBody();
    }

    public Email getById(long id) {

        ResponseEntity<Email> email = template.exchange(baseUri + GET_BY_ID + id, GET, getEntity(), new ParameterizedTypeReference<Email>() {});
        return email.getBody();
    }
}
