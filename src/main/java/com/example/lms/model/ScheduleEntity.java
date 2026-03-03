package com.example.lms.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupEntity group;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private TeacherEntity teacher;
    @Column(name = "time")
    private LocalDateTime time;
}
