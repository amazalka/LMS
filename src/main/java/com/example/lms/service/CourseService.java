package com.example.lms.service;

import com.example.lms.dto.request.CourseRequest;
import com.example.lms.dto.response.CourseResponse;
import com.example.lms.exception.CourseNotFoundException;
import com.example.lms.mapper.CourseMapper;
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
    private final CourseMapper courseMapper;

    public CourseResponse addCourse(CourseRequest request) {
        CourseEntity course = courseMapper.toEntity(request);
        courseRepository.save(course);
        return courseMapper.toResponse(course);
    }

    public CourseResponse updateCourse(Long id, CourseRequest request) {
        CourseEntity course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
        courseMapper.updateCourseFromRequest(request, course);
        return courseMapper.toResponse(course);
    }

    public void deleteCourse(Long id) {
        CourseEntity course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
        courseRepository.delete(course);
    }

    public CourseResponse getByIdCourse(Long id) {
        CourseEntity course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
        return courseMapper.toResponse(course);
    }

    public List<CourseResponse> getAllCourses() {
        return courseMapper.toResponse(courseRepository.findAll());
    }
}
