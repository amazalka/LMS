package com.example.lms.exception;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException(Long id) {
      super("Schedule with id = " + id + "not found");
    }
}

