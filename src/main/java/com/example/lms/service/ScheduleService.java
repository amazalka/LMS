package com.example.lms.service;

import com.example.lms.exception.*;
import com.example.lms.model.CourseEntity;
import com.example.lms.model.GroupEntity;
import com.example.lms.model.ScheduleEntity;
import com.example.lms.model.TeacherEntity;
import com.example.lms.repository.CourseRepository;
import com.example.lms.repository.GroupRepository;
import com.example.lms.repository.ScheduleRepository;
import com.example.lms.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    //Добавление группы на курсы
    //Назначение времени проведения курса для определенной группы
    public ScheduleEntity createSchedule(Long groupId, Long courseId, Long teacherId, LocalDateTime time) {
        GroupEntity group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));
        CourseEntity course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));
        TeacherEntity teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new TeacherNotFoundException(teacherId));
        ScheduleEntity schedule = new ScheduleEntity();
        schedule.setTime(time);
        schedule.setGroup(group);
        schedule.setTeacher(teacher);
        schedule.setCourse(course);
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

    //Изменять время проведения курса для определенной группы
    public void updateTheTimeForAGroup(LocalDateTime time, Long scheduleId) {
        ScheduleEntity schedule = scheduleRepository.findBySchedule_Id(scheduleId).orElseThrow(() -> new ScheduleNotFoundException(scheduleId));
        schedule.setTime(time);
    }

    //Удалять время проведения курса для определенной группы
    public void deleteTheTimeForAGroup(Long scheduleId) {
        ScheduleEntity schedule = scheduleRepository.findBySchedule_Id(scheduleId).orElseThrow(() -> new ScheduleNotFoundException(scheduleId));
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
