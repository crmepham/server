package com.server.common.service;

import com.server.common.model.Property;
import com.server.common.repository.PropertyRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PropertyService extends BaseService
{
    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> getAll() {

        return propertyRepository.getAll();
    }

    public Property getByExternalReference(String externalReference) {

        return propertyRepository.getByExternalReference(externalReference);
    }

    public void update(Property property) {
        propertyRepository.update(property);
    }

    public void deleteAll(long id) {
        propertyRepository.deleteAll(id);
    }
}
