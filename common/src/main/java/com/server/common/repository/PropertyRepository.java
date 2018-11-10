package com.server.common.repository;

import com.server.common.model.Property;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Repository
public class PropertyRepository extends BaseRepository
{
    private static final String GET_ALL = "properties/get-all";
    private static final String DELETE_ALL = "properties/delete-all";
    private static final String GET_BY_REFERENCE = "properties/get-by-reference/";
    private static final String CREATE = "properties/update";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    public List<Property> getAll() {

        ResponseEntity<List<Property>> res = template.exchange(baseUri + GET_ALL, GET, getEntity(), new ParameterizedTypeReference<List<Property>>() {});
        return res.getBody();
    }

    public Property getByExternalReference(String externalReference) {

        ResponseEntity<Property> property = template.exchange(baseUri + GET_BY_REFERENCE + externalReference, GET, getEntity(), new ParameterizedTypeReference<Property>() {});
        return property.getBody();
    }

    public void update(Property property) {

        template.exchange(baseUri + CREATE, POST, postJson(property), Property.class);
    }

    public void deleteAll(long id) {

        template.exchange(baseUri + DELETE_ALL, POST, new HttpEntity<>(id, getHttpHeaders(null)), Property.class);
    }
}
