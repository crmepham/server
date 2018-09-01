package com.server.frontendservice.repository;

import com.server.common.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Repository
public class MenuRepository
{
    private static final String GET_ALL_TOP_LEVEL = "menus/get-all-top-level";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    public List<Menu> getAllTopLevel() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString("john123:password".getBytes()));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<List<Menu>> res = template.exchange(baseUri + GET_ALL_TOP_LEVEL, GET, entity, new ParameterizedTypeReference<List<Menu>>() {});
        return res.getBody();
    }
}
