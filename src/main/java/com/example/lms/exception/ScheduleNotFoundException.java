package com.example.lms.exception;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException(Long id) {
      super("Schedule with id = " + id + "not found");
    }
    public ScheduleNotFoundException(Long groupId, Long courseId) {
        super("Schedule not found for groupId = " + courseId +  "and courseId = " + groupId);
    }
}
