package com.server.dataservice.service.job.handler;

import com.server.common.model.Action;
import com.server.common.model.Property;
import com.server.common.model.Reminder;
import com.server.dataservice.interfaces.JobHandler;
import com.server.dataservice.repository.ActionRepository;
import com.server.dataservice.repository.PropertyRepository;
import com.server.dataservice.repository.ReminderRepository;
import com.server.dataservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.springframework.util.StringUtils.hasText;

/**
 * Handles the sending of email notifications for reminders occurring in 1 week.
 *
 * @author Chris Mepham
 *
 */
@Component
public class RemindersOneWeekHandler implements JobHandler {

    private static final String REMINDERS_EMAIL_RECIPIENTS = "reminders_email_recipients    ";

    private static final String REMINDERS_EMAIL_SUBJECT = "REMINDER : %s events occurring in %s days.";

    private static final int DAYS = 7;

    private final Logger log = Logger.getLogger(RemindersOneWeekHandler.class.getSimpleName());

    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void execute(String className) {

        List<Action> actions = actionRepository.findByClassNameAndStateAndDeletedFalseOrderByLastUpdatedDesc(className, Action.STATE_STARTED);
        Action action = actions.iterator().next();

        // Get the date, 7 days from now
        LocalDate threshold = LocalDate.now().plusDays(DAYS);
        Date date = Date.from(threshold.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH)+1;

        // Get all reminders in 7 days
        final Collection<Reminder> reminders = reminderRepository.findByDayAndMonthAndDeletedFalse(day, month);

        if (reminders.isEmpty()) {
            action.setLastUpdated(new Date());
            action.setState(Action.STATE_COMPLETED);
            action.setResultMessage("No reminders occurring in 7 days.");
            actionRepository.save(action);
            return;
        }

        debug(format("Found %s reminders with an action date in %s days.", reminders.size(), DAYS));

        final Property recipientsProperty = propertyRepository.findByExternalReference(REMINDERS_EMAIL_RECIPIENTS);

        if (!hasText(recipientsProperty.getValue())) {
            action.setLastUpdated(new Date());
            action.setState(Action.STATE_COMPLETED);
            action.setResultMessage(format("No recipients configured to receive reminder emails. " +
                                           "Add recipients using property '%s'.", REMINDERS_EMAIL_RECIPIENTS));
            actionRepository.save(action);
        }

        final List<String> recipients = Arrays.asList(recipientsProperty.getValue().split(","));
        final String subject = format(REMINDERS_EMAIL_SUBJECT, reminders.size(), DAYS);

        emailService.queue(subject, createBody(reminders), recipients);

        action.setState(Action.STATE_COMPLETED);
        action.setResultMessage(String.format("Queued emails for the following recipients:%s\nReminders:\n%s",
                recipients, reminders.stream().map(Reminder::getInstruction).collect(Collectors.toList())));
        action.setLastUpdated(new Date());
        actionRepository.save(action);
    }

    private String createBody(Collection<Reminder> reminders) {
        StringBuilder builder = new StringBuilder();
        reminders.forEach(r -> builder.append(r.getInstruction() + "\n"));
        return builder.toString();
    }

    private void debug(String message) {
        if (log.isLoggable(Level.INFO)) {
            log.log(Level.INFO, message);
        }
    }
}
