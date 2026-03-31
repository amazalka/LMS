package com.example.lms.controller;

import com.example.lms.dto.request.ScheduleRequest;
import com.example.lms.dto.request.UpdateScheduleTimeRequest;
import com.example.lms.dto.response.ScheduleResponse;
import com.example.lms.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleResponse create(@RequestBody ScheduleRequest request) {
        return scheduleService.createSchedule(request);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return id;
    }

    @PutMapping("/{id}")
    public ScheduleResponse update(@PathVariable Long id, @RequestBody ScheduleRequest request) {
        return scheduleService.updateSchedule(id, request);
    }

    @GetMapping
    public List<ScheduleResponse> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return scheduleService.getAllSchedules(page, size);
    }

    @GetMapping("/{id}")
    public ScheduleResponse getById(@PathVariable Long id) {
        return scheduleService.getByIdSchedule(id);
    }

    @PatchMapping("/{id}/time")
    //Изменять время проведения курса для определенной группы
    public LocalDateTime updateTime(@RequestBody UpdateScheduleTimeRequest request, @PathVariable Long id) {
        scheduleService.updateTime(request.getTime(), id);
        return request.getTime();
    }

    @DeleteMapping("/{id}/time")
    //Удалять время проведения курса для определенной группы
    public void deleteTime(@PathVariable Long id) {
        scheduleService.deleteTime(id);
    }

    @GetMapping("/groups/{groupId}")
    //Просматривать график проведения курсов для каждой группы
    public List<ScheduleResponse> getTheCourseScheduleForGroup(@PathVariable Long groupId) {
        return scheduleService.getTheCourseScheduleForGroup(groupId);
    }

    @GetMapping("/teachers/{teacherId}")
    //Просматривать график занятий для каждого преподавателя
    public List<ScheduleResponse> getTheCourseScheduleForTeacher(@PathVariable Long teacherId) {
        return scheduleService.getTheCourseScheduleForTeacher(teacherId);
    }
}
