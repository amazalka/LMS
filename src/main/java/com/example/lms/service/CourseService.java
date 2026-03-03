package com.example.lms.service;

import com.example.lms.exception.CourseNotFoundException;
import com.example.lms.model.CourseEntity;
import com.example.lms.repository.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseEntity addCourse(CourseEntity course) {
        courseRepository.save(course);
        return course;
    }

    public CourseEntity updateCourse(Long id, CourseEntity updateCourse) {
        CourseEntity course = courseRepository.findById(id).orElseThrow();
        course.setName(updateCourse.getName());
        course.setDescription(updateCourse.getDescription());
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public CourseEntity getByIdCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
    }

    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }
}
