package com.example.lms.controller;

import com.example.lms.dto.request.CourseRequest;
import com.example.lms.dto.response.CourseResponse;
import com.example.lms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public CourseResponse add(@RequestBody CourseRequest course) {
        return courseService.addCourse(course);
    }

    @PutMapping("/{id}")
    public CourseResponse update(@PathVariable Long id, @RequestBody CourseRequest course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return id;
    }

    @GetMapping("/{id}")
    public CourseResponse getById(@PathVariable Long id) {
        return courseService.getByIdCourse(id);
    }

    @GetMapping
    public List<CourseResponse> getAll() {
        return courseService.getAllCourses();
    }
}