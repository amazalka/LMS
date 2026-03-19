package com.example.lms.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class UpdateScheduleTimeRequest {
    private LocalDateTime time;
}
