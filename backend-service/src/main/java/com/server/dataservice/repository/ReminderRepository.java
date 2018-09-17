package com.server.dataservice.repository;

import com.server.common.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    List<Reminder> findByDeletedFalse();
    List<Reminder> findByDayAndMonthAndDeletedFalse(int day, int month);
}
