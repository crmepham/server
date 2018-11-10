package com.server.frontendservice.service;

import com.server.common.model.Property;
import com.server.common.model.Secret;
import com.server.common.model.SecretProperty;
import com.server.common.repository.PropertyRepository;
import com.server.common.service.BaseService;
import com.server.frontendservice.repository.SecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Transactional
@Service
public class SecretService extends BaseService
{
    public static final String SECRET_TYPES = "secret_types";
    public static final String SECRET_PROPERTY_NAMES = "secret_property_names";

    @Autowired
    private SecretRepository secretRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    public CompletableFuture<List<Secret>> getAll() {

        return secretRepository.getAll();
    }

    public List<String> getAllTypes() {

        Property property = propertyRepository.getByExternalReference(SECRET_TYPES);

        return Arrays.asList(property.getValue().split(","));
    }

    public List<String> getAllPropertyNames() {

        Property property = propertyRepository.getByExternalReference(SECRET_PROPERTY_NAMES);

        return Arrays.asList(property.getValue().split(","));
    }

    public CompletableFuture<Secret> getById(long id) {

        return secretRepository.getById(id);
    }

    public void update(Secret secret) {
        secretRepository.create(secret);
    }

    public void createProperty(SecretProperty secretProperty) {
        secretRepository.createProperty(secretProperty);
    }

    public void deleteProperty(long id) {
        secretRepository.deleteProperty(id);
    }

    public CompletableFuture<List<SecretProperty>> getProperties(long id) {

        return secretRepository.getProperties(id);
    }

    public void delete(long id) {
        secretRepository.delete(id);
    }
}
