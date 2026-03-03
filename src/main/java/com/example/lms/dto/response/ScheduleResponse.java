package com.example.lms.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleResponse {
    private Long id;
    private GroupResponse group;
    private CourseShortResponse course;
    private TeacherResponse teacher;
    private LocalDateTime time;
}
