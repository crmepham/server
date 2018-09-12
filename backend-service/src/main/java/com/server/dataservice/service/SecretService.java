package com.server.dataservice.service;

import com.server.common.model.Secret;
import com.server.common.model.SecretProperty;
import com.server.dataservice.repository.SecretPropertyRepository;
import com.server.dataservice.repository.SecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecretService
{
    @Autowired
    private SecretRepository secretRepository;

    @Autowired
    private SecretPropertyRepository secretPropertyRepository;

    public List<Secret> getAll() {
        return secretRepository.findByDeletedFalse();
    }

    public Secret get(Long id) {
        return secretRepository.findById(id).get();
    }

    public void update(Secret secret) {
        secretRepository.save(secret);
    }

    public void create(Secret secret) {
        secretRepository.save(secret);
    }

    public void delete(long id) {
        secretRepository.deleteById(id);
    }

    public void createProperty(SecretProperty secretProperty) {
        secretPropertyRepository.save(secretProperty);
    }

    public void deleteProperty(long id) {
        secretPropertyRepository.deleteById(id);
    }

    public List<SecretProperty> getProperties(long id) {
        return secretPropertyRepository.findBySecretIdAndDeletedFalse(id);
    }
}
