package com.server.common.repository;

import com.server.common.model.File;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Repository
public class FileRepository extends BaseRepository
{
    private static final String GET_ALL = "files/get-all";
    private static final String GET_ALL_IMAGES = "files/get-all-images";
    private static final String GET_BY_ID = "files/get-by-id/";
    private static final String GET_BY_EXTERNAL_REFERENCE = "files/get-by-external-reference/";
    private static final String GET_BY_SHORT_REFERENCE = "files/get-by-short-reference/";
    private static final String CREATE = "files/create";
    private static final String DELETE = "files/delete";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    public CompletableFuture<List<File>> getAll() {

        String url = baseUri + GET_ALL;
        ResponseEntity<List<File>> res = template.exchange(url, GET, getEntity(), new ParameterizedTypeReference<List<File>>() {});
        return CompletableFuture.completedFuture(res.getBody());
    }

    public CompletableFuture<List<File>> getAllImages() {

        String url = baseUri + GET_ALL_IMAGES;
        ResponseEntity<List<File>> res = template.exchange(url, GET, getEntity(), new ParameterizedTypeReference<List<File>>() {});
        return CompletableFuture.completedFuture(res.getBody());
    }

    public CompletableFuture<File> getById(long id) {

        ResponseEntity<File> file = template.exchange(baseUri + GET_BY_ID + id, GET, getEntity(), new ParameterizedTypeReference<File>() {});
        return CompletableFuture.completedFuture(file.getBody());
    }

    public File getByExternalReference(final String externalReference) {

        ResponseEntity<File> file = template.exchange(baseUri + GET_BY_EXTERNAL_REFERENCE + externalReference, GET, getEntity(), new ParameterizedTypeReference<File>() {});
        return file.getBody();
    }

    public File getByShortReference(final String shortReference) {

        ResponseEntity<File> file = template.exchange(baseUri + GET_BY_SHORT_REFERENCE + shortReference, GET, getEntity(), new ParameterizedTypeReference<File>() {});
        return file.getBody();
    }

    public void create(File file) {

        template.exchange(baseUri + CREATE, POST, postJson(file), File.class);
    }

    public void delete(long id) {

        final String url = baseUri + DELETE;
        template.exchange(url, POST, postJson(id), File.class);
    }
}
