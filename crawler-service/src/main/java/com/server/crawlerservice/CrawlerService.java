package com.server.crawlerservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CrawlerService {
    public static void main(String[] args) {
        //ConfigurableApplicationContext context = SpringApplication.run(CrawlerServiceApplication.class, args);

        //context.getBean(CrawlerService.class).startCrawler();
    }
}
