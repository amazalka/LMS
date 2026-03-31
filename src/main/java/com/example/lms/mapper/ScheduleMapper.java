package com.example.lms.mapper;

import com.example.lms.dto.request.ScheduleRequest;
import com.example.lms.dto.response.ScheduleResponse;
import com.example.lms.model.ScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ScheduleMapper {
    ScheduleResponse toResponse(ScheduleEntity schedule);

    ScheduleEntity toEntity(ScheduleRequest courseRequest);

    List<ScheduleResponse> toResponse(List<ScheduleEntity> schedules);
}
