package com.server.frontendservice.repository;

import com.server.common.repository.BaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpMethod.GET;

@Repository
public class SearchRepository extends BaseRepository
{
    private static final String SEARCH = "search/";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    public CompletableFuture<Map<String, List<Object>>> search(String text) {
        ResponseEntity<Map<String, List<Object>>> res =
                template.exchange(baseUri + SEARCH + text, GET, getEntity(),
                        new ParameterizedTypeReference<Map<String, List<Object>>>() {});
        return CompletableFuture.completedFuture(res.getBody());
    }

}
