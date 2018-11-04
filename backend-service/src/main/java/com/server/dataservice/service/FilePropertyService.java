package com.server.dataservice.service;

import com.server.common.model.FileProperty;
import com.server.dataservice.repository.FilePropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilePropertyService
{
    @Autowired
    private FilePropertyRepository filePropertyRepository;

    public void deleteAll(long id) {

        final List<FileProperty> properties = filePropertyRepository.findByFileIdAndDeletedFalse(id);
        for (FileProperty property : properties) {
            filePropertyRepository.delete(property);
        }
    }
}
