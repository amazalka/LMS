package com.example.lms.service;

import com.example.lms.exception.GroupNotFoundException;
import com.example.lms.exception.StudentNotFoundException;
import com.example.lms.model.GroupEntity;
import com.example.lms.model.StudentEntity;
import com.example.lms.repository.GroupRepository;
import com.example.lms.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentEntity addStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public StudentEntity updateStudent(Long id, StudentEntity updateStudent) {
        StudentEntity student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        student.setName(updateStudent.getName());
        student.setLastName(updateStudent.getLastName());
        student.setGroup(updateStudent.getGroup());
        return student;
    }

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentEntity getByIdStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    // Объединение студентов в группы
    public List<StudentEntity> combineIntoGroups(Long groupId, List<Long> studentsId){
        GroupEntity group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));
        List<StudentEntity> students = studentRepository.findAllById(studentsId);
        for(StudentEntity student:students){
            student.setGroup(group);
        }
        return students;
    }
}
