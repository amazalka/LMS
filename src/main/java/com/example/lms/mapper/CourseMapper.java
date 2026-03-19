package com.example.lms.mapper;

import com.example.lms.dto.request.CourseRequest;
import com.example.lms.dto.response.CourseResponse;
import com.example.lms.model.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {
    CourseResponse toResponse(CourseEntity course);
    CourseEntity toEntity(CourseRequest courseRequest);
}
