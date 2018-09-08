package com.server.frontendservice.service;

import com.server.common.model.Property;
import com.server.frontendservice.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
