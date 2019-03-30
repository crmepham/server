package com.server.frontendservice.repository;

import static org.springframework.http.HttpMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.server.common.repository.BaseRepository;

@Repository
public class SystemRepository extends BaseRepository
{
    private static final String SYSTEM_STOP = "system/stop";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String backendUri;

    @Value("${base.crawler.uri}")
    private String crawlerUri;

    @Async("asyncExecutor")
    public void stopBackend() {
        template.exchange(backendUri + SYSTEM_STOP, POST, getEntity(), Void.class);
    }

    @Async("asyncExecutor")
    public void stopCrawler() {
        template.exchange(crawlerUri + SYSTEM_STOP, POST, getEntity(), Void.class);
    }
}
