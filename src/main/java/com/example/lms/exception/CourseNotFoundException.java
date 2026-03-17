package com.example.lms.exception;

public class CourseNotFoundException extends NotFoundException {
    public CourseNotFoundException(Long id) {
        super("Course with id = " + id + " not found");
    }
}