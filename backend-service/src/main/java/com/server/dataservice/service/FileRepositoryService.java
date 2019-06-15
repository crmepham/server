package com.server.dataservice.service;

import com.server.common.model.File;
import com.server.dataservice.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileRepositoryService
{
    @Autowired
    private FileRepository fileRepository;

    public List<File> getAll() {
        return fileRepository.findByDeletedFalseOrderByCreatedDesc();
    }

    public List<File> getAllImages() {
        return fileRepository.findByTypeAndDeletedFalseOrderByCreatedDesc("Image");
    }

    public List<File> getByType(String type, int page) {
        return fileRepository.findByTypeAndDeletedFalseAndAbsolutePathNotNullOrderByCreatedDesc(type, PageRequest.of(page, 30));
    }

    public File get(Long id) {
        return fileRepository.findById(id).get();
    }

    public File get(final String externalReference) {
        return fileRepository.findByExternalReference(externalReference);
    }

    public File getByShortReference(final String shortReference) {
        return fileRepository.findByShortReference(shortReference);
    }

    public void create(File file) {
        fileRepository.save(file);
    }

    public void delete(long id) {
        fileRepository.deleteById(id);
    }
}
