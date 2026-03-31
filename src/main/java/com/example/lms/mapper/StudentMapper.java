package com.example.lms.mapper;

import com.example.lms.dto.request.StudentRequest;
import com.example.lms.dto.response.StudentResponse;
import com.example.lms.model.StudentEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    StudentResponse toResponse(StudentEntity student);

    StudentEntity toEntity(StudentRequest studentRequest);

    void updateStudentFromRequest(@MappingTarget StudentEntity student, StudentRequest request);

    List<StudentResponse> toResponse(List<StudentEntity> students);
}
