package com.example.lms.service;

import com.example.lms.dto.request.StudentRequest;
import com.example.lms.dto.request.StudentSearchRequest;
import com.example.lms.dto.response.StudentResponse;
import com.example.lms.exception.*;
import com.example.lms.mapper.StudentMapper;
import com.example.lms.model.*;
import com.example.lms.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
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

    public List<StudentResponse> getAllStudents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentEntity> studentPage = studentRepository.findAll(pageable);
        return studentMapper.toResponse(studentPage.getContent());
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

    public List<StudentResponse> search(StudentSearchRequest request){
        return studentMapper.toResponse(studentRepository.search(request));
    }
}

