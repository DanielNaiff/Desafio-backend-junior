package com.example.alunos.dto;

import java.time.LocalDate;

public record EnrollmentDTO(String enrollmentCode, String courseName, LocalDate beginingDate) {
}
