package com.example.lms.exception;

public class StudentNotFoundException extends NotFoundException {
    public StudentNotFoundException(Long id) {
        super("Student with id = " + id + " not found");
    }
    public StudentNotFoundException() {
        super("Students not found");
    }
}