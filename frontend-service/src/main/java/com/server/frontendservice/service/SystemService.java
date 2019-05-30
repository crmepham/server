package com.server.frontendservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.common.service.BaseService;
import com.server.frontendservice.repository.SystemRepository;

@Transactional
@Service
public class SystemService extends BaseService {

    @Autowired
    private SystemRepository systemRepository;

    public void stopFrontend() {
        System.exit(0);
    }

    public void stopBackend() {
        systemRepository.stopBackend();
    }

    public void stopCrawler() {
        systemRepository.stopCrawler();
    }

}
