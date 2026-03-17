package com.example.lms.exception;

public class TimeIsSetException extends NotFoundException {
    public TimeIsSetException(String message) {
        super(message);
    }
}