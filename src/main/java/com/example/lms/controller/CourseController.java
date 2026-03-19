package com.example.lms.controller;

import com.example.lms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/Courses")
public class CourseController {
    public CourseService courseService;

    public CourseResponse add (@RequestBody CourseRequest course){
        courseService.addCourse(course);
    }
}
