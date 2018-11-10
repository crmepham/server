package com.server.common.repository;

import com.google.gson.Gson;

import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class BaseRepository
{
    protected HttpEntity getEntity() {

        HttpHeaders headers = getHttpHeaders(null);
        return new HttpEntity<>("parameters", headers);
    }

    protected HttpEntity postJson(Object instance) {

        HttpHeaders headers = getHttpHeaders(null);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(new Gson().toJson(instance), headers);
    }

    protected HttpHeaders getHttpHeaders(HttpHeaders headers) {

        if (headers == null) {
            headers = new HttpHeaders();
        }
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString("john123:password".getBytes()));
        return headers;
    }
}
