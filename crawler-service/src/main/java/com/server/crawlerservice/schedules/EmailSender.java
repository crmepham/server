package com.server.crawlerservice.schedules;

import com.server.crawlerservice.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    @Autowired
    private EmailRepository emailRepository;

    /**
     * Get the data service to send any pending
     * emails every 5 minutes.
     */
    //@Scheduled(fixedRate = 3000)
    @Scheduled(cron = "*/5 * * * * ?")
    public void sendQueuedEmails() {
        emailRepository.send();
    }
}
