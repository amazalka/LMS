package com.example.lms.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleRequest {
    private Long groupId;
    private Long courseId;
    private Long teacherId;
    private LocalDateTime time;
}
