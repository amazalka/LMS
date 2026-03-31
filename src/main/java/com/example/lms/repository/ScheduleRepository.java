package com.example.lms.repository;

import com.example.lms.model.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findAllByGroup_Id(Long groupId);

    List<ScheduleEntity> findAllByTeacher_Id(Long teacherId);

    void deleteAllByTimeBefore(LocalDateTime time);
}
