package com.example.lms.exception;

public class TeacherNotFoundException extends NotFoundException {
    public TeacherNotFoundException(Long id) {
        super("Teacher with id = " + id + " not found");
    }
}

