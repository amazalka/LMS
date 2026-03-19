package com.example.lms.exception;

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(Long id) {
        super("Group with id = " + id + "not found");
    }
}
