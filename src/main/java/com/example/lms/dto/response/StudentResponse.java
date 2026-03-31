package com.example.lms.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String name;
    private String lastName;
    private GroupResponse group;
}
