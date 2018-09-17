package com.server.crawlerservice.repository;

import com.server.common.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Repository
public class EmailRepository extends BaseRepository
{
    private static final String SEND = "emails/send";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    public void send() {
        template.exchange(baseUri + SEND, GET, getEntity(), new ParameterizedTypeReference<List<Email>>() {});
    }
}
