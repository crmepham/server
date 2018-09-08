package com.server.dataservice.service;

import com.server.common.model.Property;
import com.server.dataservice.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService
{
    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> getAll() {
        return propertyRepository.findByDeletedFalse();
    }

    public Property get(String externalReference) {
        return propertyRepository.findByExternalReference(externalReference);
    }

    public void update(Property property) {
        propertyRepository.save(property);
    }
}
