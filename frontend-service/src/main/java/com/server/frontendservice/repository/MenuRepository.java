package com.server.frontendservice.repository;

import static org.springframework.http.HttpMethod.GET;

import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.server.common.model.Menu;
import lombok.val;

@Repository
public class MenuRepository {
    private static final String GET_ALL_TOP_LEVEL = "menus/get-all-top-level";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    public CompletableFuture<List<Menu>> getAllTopLevel() {
        val headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString("john123:password".getBytes()));

        val entity = new HttpEntity<>("parameters", headers);
        val res = template.exchange(baseUri + GET_ALL_TOP_LEVEL, GET, entity, new ParameterizedTypeReference<List<Menu>>() {});
        return CompletableFuture.completedFuture(res.getBody());
    }
}
