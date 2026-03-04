package com.example.lms.repository;

import com.example.lms.model.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findAllByGroup_Id(Long groupId);
    List<ScheduleEntity> findAllByTeacher_Id(Long teacherId);
    Optional<ScheduleEntity> findByGroup_IdAndCourse_Id (Long groupId, Long courseId);
    Optional<ScheduleEntity> findBySchedule_Id (Long scheduleId);
}
