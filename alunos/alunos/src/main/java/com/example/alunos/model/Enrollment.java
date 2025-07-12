package com.example.alunos.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String enrollmentCode;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private LocalDate beginingDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
