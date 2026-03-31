package com.example.lms.repository;

import com.example.lms.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>, StudentRepositoryCustom {
}
