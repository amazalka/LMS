package com.example.lms.exception;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(Long id) {
      super("Teacher with id = " + id + "not found");
    }
}
