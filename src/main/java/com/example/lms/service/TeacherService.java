package com.example.lms.service;

import com.example.lms.exception.TeacherNotFoundException;
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

    public TeacherEntity addTeacher(TeacherEntity teacher) {
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    public TeacherEntity updateTeacher(Long id, TeacherEntity updateTeacher) {
        TeacherEntity teacher = teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
        teacher.setName(updateTeacher.getName());
        teacher.setLastName(updateTeacher.getLastName());
        return teacher;
    }

    public List<TeacherEntity> getAllTeachers(){
        return teacherRepository.findAll();
    }

    public TeacherEntity getByIdTeacher(Long id){
        return teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
    }
}
