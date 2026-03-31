package com.example.lms.repository;

import com.example.lms.dto.request.StudentSearchRequest;
import com.example.lms.model.StudentEntity;

import java.util.List;

public interface StudentRepositoryCustom {
    List<StudentEntity> search(StudentSearchRequest request);
}
