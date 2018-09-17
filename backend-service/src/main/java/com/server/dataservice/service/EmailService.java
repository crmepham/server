package com.server.dataservice.service;

import com.server.common.model.Email;
import com.server.dataservice.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

@Service
public class EmailService {

    private static final String FROM = "crmepham@hotmail.co.uk";

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Value("${email.from}")
    private String emailFrom;

    @Value("${email.retry.threshold}")
    private int emailRetryCount;

    public void send() {

        List<Email> emails = emailRepository.findBySentFalseAndRetryCountLessThanAndDeletedFalse(emailRetryCount);
        if (emails.isEmpty()) {
            return;
        }

        emails.forEach(e -> send(e));
    }

    public void queue(String subject, String body, List<String> recipients) {

        if (recipients.isEmpty()) {
            return;
        }

        List<Email> emails = new ArrayList<>();
        recipients.forEach(r -> emails.add(new Email(r, FROM, subject, body)));
        emailRepository.saveAll(emails);
    }

    private void send(Email email) {

        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            // Incase you need to send attachement: helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));

            helper.setTo(email.getTo());
            helper.setText(email.getBody(), false);
            helper.setSubject(email.getSubject());
            helper.setFrom(email.getFrom());
            emailSender.send(message);

            // If bulk sending is need see: https://github.com/nithril/smtp-connection-pool

        } catch (Exception e) {
            email.setRetryCount(email.getRetryCount()+1);
            email.setState(Email.STATE_FAILED);
            email.setFailureReason(format("Failed to send email:%n%s", e.getMessage()));
            email.setLastUpdated(new Date());
            emailRepository.save(email);
        }
    }
}
