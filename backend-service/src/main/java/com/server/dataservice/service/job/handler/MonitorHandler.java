package com.server.dataservice.service.job.handler;

import static java.lang.String.format;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.util.StringUtils.hasText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.server.common.model.Action;
import com.server.common.model.Monitor;
import com.server.common.model.Property;
import com.server.dataservice.interfaces.JobHandler;
import com.server.dataservice.repository.ActionRepository;
import com.server.dataservice.repository.MonitorRepository;
import com.server.dataservice.repository.PropertyRepository;
import com.server.dataservice.service.EmailService;

/**
 * Monitors specified servers and queues emails listing those servers that
 * are unreachable.
 *
 * @author Chris Mepham
 */
@Component
public class MonitorHandler implements JobHandler {

    private static final String MONITOR_EMAIL_RECIPIENTS = "monitor_email_recipients";

    private static final String MONITOR_EMAIL_SUBJECT = "MONITOR : %s servers are unreachable.";

    private static final List<Integer> VALID_CODE_PREFIXES = new ArrayList<>();
    static {
        VALID_CODE_PREFIXES.add(2);
        VALID_CODE_PREFIXES.add(3);
    }

    private final Logger log = Logger.getLogger(MonitorHandler.class.getSimpleName());

    @Autowired
    private MonitorRepository monitorRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void execute(String className) {

        final List<Action> actions = actionRepository.findByClassNameAndStateAndDeletedFalseOrderByLastUpdatedDesc(className, Action.STATE_STARTED);
        final Action action = actions.iterator().next();

        // Get the list of servers to monitor
        final List<Monitor> servers = monitorRepository.findByDeletedFalse();
        if (servers.isEmpty()) {
            action.setLastUpdated(new Date());
            action.setState(Action.STATE_COMPLETED);
            action.setResultMessage("No servers to monitor.");
            actionRepository.save(action);
            return;
        }

        // Get the list of recipients
        final Property recipientsProperty = propertyRepository.findByExternalReference(MONITOR_EMAIL_RECIPIENTS);
        if (!hasText(recipientsProperty.getValue())) {
            action.setLastUpdated(new Date());
            action.setState(Action.STATE_COMPLETED);
            action.setResultMessage(format("No recipients configured to receive monitor emails. " +
                    "Add recipients using property '%s'.", MONITOR_EMAIL_RECIPIENTS));
            actionRepository.save(action);
            return;
        }

        debug(format("Found %s servers to monitor", servers.size()));

        // Ping servers
        ping(servers);

        final List<Monitor> unreachable = servers.stream().filter(s -> !s.getReachable() && s.getNotify()).collect(Collectors.toList());
        if (unreachable.isEmpty()) {
            action.setLastUpdated(new Date());
            action.setState(Action.STATE_COMPLETED);
            action.setResultMessage("No unreachable servers that be notified.");
            actionRepository.save(action);
            return;
        }

        debug(format("Found %s unreachable servers.", unreachable.size()));

        final List<String> recipients = Arrays.asList(recipientsProperty.getValue().split(","));
        final String subject = format(MONITOR_EMAIL_SUBJECT, unreachable.size());

        emailService.queue(subject, createBody(unreachable), recipients);

        action.setState(Action.STATE_COMPLETED);
        action.setResultMessage(String.format("Queued emails for the following monitor recipients:%s\nServers:\n%s",
                recipients, unreachable.stream().map(Monitor::getUri).collect(Collectors.toList())));
        action.setLastUpdated(new Date());
        actionRepository.save(action);
    }

    private void ping(@NonNull final List<Monitor> servers) {
        servers.forEach( s -> {
            try
            {
                final ResponseEntity<String> res = restTemplate.exchange(s.getUri(), GET, new HttpEntity<>(new HttpHeaders()), new ParameterizedTypeReference<String>() {});
                final int prefix = Integer.parseInt(String.valueOf(res.getStatusCodeValue()).substring(0, 1));
                s.setReachable(VALID_CODE_PREFIXES.contains(prefix));
            } catch (final Exception e) {
                s.setReachable(false);
            }
            s.setLastUpdated(new Date());
        });
        monitorRepository.saveAll(servers);
    }

    private String createBody(@NonNull final Collection<Monitor> servers) {
        StringBuilder builder = new StringBuilder();
        servers.forEach(s -> builder.append(s.getUri() + "\n"));
        return builder.toString();
    }

    private void debug(String message) {
        if (log.isLoggable(Level.INFO)) {
            log.log(Level.INFO, message);
        }
    }
}
