package com.example.lms.controller;

import com.example.lms.dto.request.StudentRequest;
import com.example.lms.dto.response.StudentResponse;
import com.example.lms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public StudentResponse add(@RequestBody StudentRequest request) {
        return studentService.addStudent(request);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return id;
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id, @RequestBody StudentRequest request) {
        return studentService.updateStudent(id, request);
    }

    @GetMapping
    public List<StudentResponse> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponse getById(@PathVariable Long id) {
        return studentService.getByIdStudent(id);
    }

    @PostMapping("/groups/{groupId}")
    public List<StudentResponse> addIntoGroups(@PathVariable Long groupId, @RequestBody List<Long> studentsId) {
        return studentService.addIntoGroups(groupId, studentsId);
    }
}