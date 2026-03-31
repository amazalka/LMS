package com.example.lms.controller;

import com.example.lms.dto.request.TeacherRequest;
import com.example.lms.dto.response.TeacherResponse;
import com.example.lms.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping
    public TeacherResponse add(@RequestBody TeacherRequest request) {
        return teacherService.addTeacher(request);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return id;
    }

    @PutMapping("/{id}")
    public TeacherResponse update(@PathVariable Long id, @RequestBody TeacherRequest request) {
        return teacherService.updateTeacher(id, request);
    }

    @GetMapping
    public List<TeacherResponse> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return teacherService.getAllTeachers(page, size);
    }

    @GetMapping("/{id}")
    public TeacherResponse getById(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }
}
