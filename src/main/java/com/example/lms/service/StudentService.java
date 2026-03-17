package com.example.lms.service;

import com.example.lms.dto.request.StudentRequest;
import com.example.lms.dto.response.StudentResponse;
import com.example.lms.exception.GroupNotFoundException;
import com.example.lms.exception.StudentNotFoundException;
import com.example.lms.mapper.StudentMapper;
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
    private final StudentMapper studentMapper;

    public StudentResponse addStudent(StudentRequest request) {
        StudentEntity student = studentMapper.toEntity(request);
        studentRepository.save(student);
        return studentMapper.toResponse(student);
    }

    public void deleteStudent(Long id) {
        StudentEntity student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
    }

    public StudentResponse updateStudent(Long id, StudentRequest request) {
        StudentEntity student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentMapper.updateStudentFromRequest(student, request);
        GroupEntity group = groupRepository.findById(request.getGroupId()).orElseThrow(() -> new GroupNotFoundException(request.getGroupId()));
        student.setGroup(group);
        return studentMapper.toResponse(student);
    }

    public List<StudentResponse> getAllStudents() {
        return studentMapper.toResponse(studentRepository.findAll());
    }

    public StudentResponse getByIdStudent(Long id) {
        StudentEntity student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        return studentMapper.toResponse(student);
    }

    // Объединение студентов в группы
    public List<StudentResponse> addIntoGroups(Long groupId, List<Long> studentsId) {
        GroupEntity group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));
        List<StudentEntity> students = studentRepository.findAllById(studentsId);
        if (students.size() != studentsId.size()) {
            throw new StudentNotFoundException();
        }
        for (StudentEntity student : students) {
            student.setGroup(group);
        }
        return studentMapper.toResponse(students);
    }
}

