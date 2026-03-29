package com.example.lms.job;

import com.example.lms.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
@Transactional
public class ScheduleDeleteJob {
    private final ScheduleRepository scheduleRepository;

    //Удалять все расписание, у которого занятие прошло более 1 года назад
    @Scheduled(cron = "${scheduler.delete-old-schedules.cron}")
    public void deleteOldSchedules() {
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime timeYearAgo = timeNow.minusYears(1);
        scheduleRepository.deleteAllByTimeBefore(timeYearAgo);
    }
}
