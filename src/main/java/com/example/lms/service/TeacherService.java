package com.example.lms.service;

import com.example.lms.dto.request.TeacherRequest;
import com.example.lms.dto.response.TeacherResponse;
import com.example.lms.exception.TeacherNotFoundException;
import com.example.lms.mapper.TeacherMapper;
import com.example.lms.model.TeacherEntity;
import com.example.lms.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherResponse addTeacher(TeacherRequest request) {
        TeacherEntity teacher = teacherMapper.toEntity(request);
        teacherRepository.save(teacher);
        return teacherMapper.toResponse(teacher);
    }

    public void deleteTeacher(Long id) {
        TeacherEntity teacher = teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
        teacherRepository.delete(teacher);
    }

    public TeacherResponse updateTeacher(Long id, TeacherRequest request) {
        TeacherEntity teacher = teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
        teacherMapper.updateTeacherFromRequest(teacher, request);
        return teacherMapper.toResponse(teacher);
    }

    public List<TeacherResponse> getAllTeachers() {
        return teacherMapper.toResponse(teacherRepository.findAll());
    }

    public TeacherResponse getTeacherById(Long id) {
        return teacherMapper.toResponse(teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id)));
    }
}