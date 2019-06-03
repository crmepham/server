package com.server.common.service;

import java.util.UUID;

import com.server.common.model.ApplicationError;
import com.server.common.model.BaseEntity;
import com.server.common.repository.ErrorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseService
{
    @Autowired
    private ErrorRepository errorRepository;

    @Autowired
    protected FileService fileService;

    public void persistError(Exception e, String context) {

        ApplicationError error = new ApplicationError();
        error.setContext(context);
        error.setException(e.getClass().getSimpleName());
        error.setStackTrace(e.getMessage());
        errorRepository.create(error);
    }

    public <T extends BaseEntity> void persistError(Exception e, String context, String entityName, String entityReference, long entityId) {

        ApplicationError error = new ApplicationError();
        String name = entityName;
        error.setContext(context);
        error.setEntity(name.substring(name.lastIndexOf(".") + 1));
        error.setEntityId(entityId);
        error.setException(e.getClass().getSimpleName());
        error.setStackTrace(e.getMessage());
        error.setEntityReference(entityReference);
        errorRepository.create(error);
    }


    protected String getShortReference() {
        String shortReference = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5).toLowerCase();
        while (fileService.getByShortReference(shortReference) != null) {
            shortReference = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5).toLowerCase();
        }
        return shortReference;
    }
}
