package com.server.frontendservice.repository;

import com.server.common.model.Dashboard;
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
public class DashboardRepository extends BaseRepository
{
    private static final String GET_ALL = "dashboards/get-all";
    private static final String GET_BY_ID = "dashboards/get-by-id/";
    private static final String UPDATE = "dashboards/update";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    public CompletableFuture<List<Dashboard>> getAll() {

        ResponseEntity<List<Dashboard>> res = template.exchange(baseUri + GET_ALL, GET, getEntity(), new ParameterizedTypeReference<List<Dashboard>>() {});
        return CompletableFuture.completedFuture(res.getBody());
    }

    public Dashboard get(Long id) {

        ResponseEntity<Dashboard> res = template.exchange(baseUri + GET_BY_ID + id, GET, getEntity(), Dashboard.class);
        return res.getBody();
    }

    public void update(Dashboard dashboard) {

        template.exchange(baseUri + UPDATE, POST, postJson(dashboard), Dashboard.class);
    }
}
