package com.example.lms.repository;

import com.example.lms.dto.request.StudentSearchRequest;
import com.example.lms.model.GroupEntity;
import com.example.lms.model.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<StudentEntity> search(StudentSearchRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StudentEntity> query = criteriaBuilder.createQuery(StudentEntity.class);
        Root<StudentEntity> student = query.from(StudentEntity.class);
        Join<StudentEntity, GroupEntity> groupJoin = student.join("group", JoinType.LEFT);
        List<Predicate> predicates = new ArrayList<>();
        if (request.getName() != null && !request.getName().isBlank()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(student.get("name")), "%" + request.getName().toLowerCase() + "%"));
        }
        if (request.getLastName() != null && !request.getLastName().isBlank()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(student.get("lastName")), "%" + request.getLastName().toLowerCase() + "%"));
        }
        if (request.getGroup() != null && !request.getGroup().isBlank()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(groupJoin.get("name")), "%" + request.getGroup().toLowerCase() + "%"));
        }
        if (!predicates.isEmpty()) {
            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }
        return entityManager.createQuery(query).getResultList();
    }
}
