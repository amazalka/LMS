package com.example.lms.service;

import com.example.lms.exception.*;
import com.example.lms.model.ScheduleEntity;
import com.example.lms.repository.CourseRepository;
import com.example.lms.repository.GroupRepository;
import com.example.lms.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RequiredArgsConstructor
@Service
@Transactional
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    //Добавление группы на курсы
    //Назначение времени проведения курса для определенной группы
    public ScheduleEntity addSchedule(ScheduleEntity schedule) {
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    public ScheduleEntity updateSchedule(Long id, ScheduleEntity updateSchedule) {
        ScheduleEntity schedule = scheduleRepository.findById(id).orElseThrow(() -> new ScheduleNotFoundException(id));
        schedule.setGroup(updateSchedule.getGroup());
        schedule.setTime(updateSchedule.getTime());
        schedule.setTeacher(updateSchedule.getTeacher());
        schedule.setCourse(updateSchedule.getCourse());
        return schedule;
    }

    public List<ScheduleEntity> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public ScheduleEntity getByIdSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new ScheduleNotFoundException(id));
    }

    //Добавлять группы на курсы
    public ScheduleEntity assignGroupToCourse(Long groupId, Long courseId) {
        ScheduleEntity schedule = new ScheduleEntity();
        schedule.setGroup(groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId)));
        schedule.setCourse(courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId)));
        return scheduleRepository.save(schedule);
    }

    //Назначать время проведения курса для определенной группы
    public ScheduleEntity assignCourseTimeForAGroup (LocalDateTime time, Long groupId, Long courseId){
        ScheduleEntity schedule = scheduleRepository.findByGroup_IdAndCourse_Id(groupId, courseId).orElseThrow(() -> new ScheduleNotFoundException(groupId, courseId));
        if (schedule.getTime() != null){
            throw new TimeIsSetException("Время уже назначено");
        }
        schedule.setTime(time);
        return schedule;
    }

    //Изменять время проведения курса для определенной группы
    public void updateTheTimeForAGroup(LocalDateTime time, Long groupId, Long courseId) {
        ScheduleEntity schedule = scheduleRepository.findByGroup_IdAndCourse_Id(groupId, courseId).orElseThrow(() -> new ScheduleNotFoundException(groupId, courseId));
        schedule.setTime(time);
    }

    //Удалять время проведения курса для определенной группы
    public void deleteTheTimeForAGroup(Long groupId, Long courseId) {
        ScheduleEntity schedule = scheduleRepository.findByGroup_IdAndCourse_Id(groupId, courseId).orElseThrow(() -> new ScheduleNotFoundException(groupId, courseId));
        schedule.setTime(null);
    }

    //Просматривать график проведения курсов для каждой группы
    public List<ScheduleEntity> getTheCourseScheduleForGroup(Long groupId) {
        return scheduleRepository.findAllByGroup_Id(groupId);
    }

    //Просматривать график занятий для каждого преподавателя
    public List<ScheduleEntity> getTheCourseScheduleForTeacher(Long teacherId) {
        return scheduleRepository.findAllByTeacher_Id(teacherId);
    }
}
