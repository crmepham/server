package com.server.frontendservice.repository;

import com.server.common.model.Reminder;
import com.server.common.model.Secret;
import com.server.common.repository.BaseRepository;

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
public class ReminderRepository extends BaseRepository
{
    private static final String GET_ALL = "reminders/get-all";
    private static final String GET_BY_ID = "reminders/get-by-id/";
    private static final String CREATE = "reminders/create";
    private static final String DELETE = "reminders/delete";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    public CompletableFuture<List<Reminder>> getAll() {

        ResponseEntity<List<Reminder>> res = template.exchange(baseUri + GET_ALL, GET, getEntity(), new ParameterizedTypeReference<List<Reminder>>() {});
        return CompletableFuture.completedFuture(res.getBody());
    }

    public CompletableFuture<Reminder> getById(long id) {

        ResponseEntity<Reminder> reminder = template.exchange(baseUri + GET_BY_ID + id, GET, getEntity(), new ParameterizedTypeReference<Reminder>() {});
        return CompletableFuture.completedFuture(reminder.getBody());
    }

    public void create(Reminder reminder) {

        template.exchange(baseUri + CREATE, POST, postJson(reminder), Reminder.class);
    }

    public void delete(long id) {

        final String url = baseUri + DELETE;
        template.exchange(url, POST, postJson(id), Reminder.class);
    }
}
