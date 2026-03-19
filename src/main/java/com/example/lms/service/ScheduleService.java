package com.example.lms.service;

import com.example.lms.dto.request.ScheduleRequest;
import com.example.lms.dto.response.ScheduleResponse;
import com.example.lms.exception.*;
import com.example.lms.mapper.ScheduleMapper;
import com.example.lms.model.*;
import com.example.lms.repository.*;
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
    private final ScheduleMapper scheduleMapper;

    //Добавление группы на курсы
    //Назначение времени проведения курса для определенной группы
    public ScheduleResponse createSchedule(ScheduleRequest request) {
        GroupEntity group = groupRepository.findById(request.getGroupId()).orElseThrow(() -> new GroupNotFoundException(request.getGroupId()));
        CourseEntity course = courseRepository.findById(request.getCourseId()).orElseThrow(() -> new CourseNotFoundException(request.getCourseId()));
        TeacherEntity teacher = teacherRepository.findById(request.getTeacherId()).orElseThrow(() -> new TeacherNotFoundException(request.getTeacherId()));
        ScheduleEntity schedule = new ScheduleEntity();
        schedule.setTime(request.getTime());
        schedule.setGroup(group);
        schedule.setTeacher(teacher);
        schedule.setCourse(course);
        ScheduleEntity savedSchedule = scheduleRepository.save(schedule);
        return scheduleMapper.toResponse(savedSchedule);
    }

    public void deleteSchedule(Long id) {
        ScheduleEntity schedule = scheduleRepository.findById(id).orElseThrow(() -> new ScheduleNotFoundException(id));
        scheduleRepository.delete(schedule);
    }

    public ScheduleResponse updateSchedule(Long id, ScheduleRequest request) {
        ScheduleEntity schedule = scheduleRepository.findById(id).orElseThrow(() -> new ScheduleNotFoundException(id));
        GroupEntity group = groupRepository.findById(request.getGroupId()).orElseThrow(() -> new GroupNotFoundException(request.getGroupId()));
        CourseEntity course = courseRepository.findById(request.getCourseId()).orElseThrow(() -> new CourseNotFoundException(request.getCourseId()));
        TeacherEntity teacher = teacherRepository.findById(request.getTeacherId()).orElseThrow(() -> new TeacherNotFoundException(request.getTeacherId()));
        schedule.setGroup(group);
        schedule.setTime(request.getTime());
        schedule.setTeacher(teacher);
        schedule.setCourse(course);
        return scheduleMapper.toResponse(schedule);
    }

    public List<ScheduleResponse> getAllSchedules() {
        return scheduleMapper.toResponse(scheduleRepository.findAll());
    }

    public ScheduleResponse getByIdSchedule(Long id) {
        ScheduleEntity schedule = scheduleRepository.findById(id).orElseThrow(() -> new ScheduleNotFoundException(id));
        return scheduleMapper.toResponse(schedule);
    }

    //Изменять время проведения курса для определенной группы
    public void updateTime(LocalDateTime time, Long scheduleId) {
        ScheduleEntity schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ScheduleNotFoundException(scheduleId));
        schedule.setTime(time);
    }

    //Удалять время проведения курса для определенной группы
    public void deleteTime(Long scheduleId) {
        ScheduleEntity schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ScheduleNotFoundException(scheduleId));
        schedule.setTime(null);
    }

    //Просматривать график проведения курсов для каждой группы
    public List<ScheduleResponse> getTheCourseScheduleForGroup(Long groupId) {
        return scheduleMapper.toResponse(scheduleRepository.findAllByGroup_Id(groupId));
    }

    //Просматривать график занятий для каждого преподавателя
    public List<ScheduleResponse> getTheCourseScheduleForTeacher(Long teacherId) {
        return scheduleMapper.toResponse(scheduleRepository.findAllByTeacher_Id(teacherId));
    }
}
