package com.example.lms.mapper;

import com.example.lms.dto.request.TeacherRequest;
import com.example.lms.dto.response.TeacherResponse;
import com.example.lms.model.TeacherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeacherMapper {
    TeacherResponse toResponse(TeacherEntity course);
    TeacherEntity toEntity(TeacherRequest courseRequest);
}
