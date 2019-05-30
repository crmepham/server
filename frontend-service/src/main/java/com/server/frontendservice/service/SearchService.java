package com.server.frontendservice.service;

import static org.springframework.util.StringUtils.hasText;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.common.service.BaseService;
import com.server.frontendservice.repository.SearchRepository;

@Transactional
@Service
public class SearchService extends BaseService {
    @Autowired
    private SearchRepository searchRepository;

    public CompletableFuture<Map<String, List<Object>>> search(String text) {
        if (!hasText(text)) {
            return CompletableFuture.completedFuture(Collections.emptyMap());
        }
        return searchRepository.search(text);
    }
}
