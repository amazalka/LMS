package com.example.lms.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSearchRequest {
    private String name;
    private String lastName;
    private String group;
}
