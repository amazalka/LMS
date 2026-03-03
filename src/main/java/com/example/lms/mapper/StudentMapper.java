package com.example.lms.mapper;

import com.example.lms.dto.request.StudentRequest;
import com.example.lms.dto.response.StudentResponse;
import com.example.lms.model.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    StudentResponse toResponse(StudentEntity course);
    StudentEntity toEntity(StudentRequest courseRequest);
}

