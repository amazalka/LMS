package com.example.lms.mapper;

import com.example.lms.dto.request.ScheduleRequest;
import com.example.lms.dto.response.ScheduleResponse;
import com.example.lms.model.ScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ScheduleMapper {
    ScheduleResponse toResponse(ScheduleEntity course);
    ScheduleEntity toEntity(ScheduleRequest courseRequest);
}
