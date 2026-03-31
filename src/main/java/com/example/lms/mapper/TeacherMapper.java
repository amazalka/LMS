package com.example.lms.mapper;

import com.example.lms.dto.request.TeacherRequest;
import com.example.lms.dto.response.TeacherResponse;
import com.example.lms.model.TeacherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeacherMapper {
    TeacherResponse toResponse(TeacherEntity teacher);

    TeacherEntity toEntity(TeacherRequest teacherRequest);

    List<TeacherResponse> toResponse(List<TeacherEntity> teachers);

    void updateTeacherFromRequest(@MappingTarget TeacherEntity teacher, TeacherRequest teacherRequest);
}