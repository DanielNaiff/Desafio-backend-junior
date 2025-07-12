package com.example.alunos.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.List;

public record StudentRequestDTO(
        String name,
        String phoneNumber,
        LocalDate birthday,
        List<EnrollmentDTO> enrollments) {


}
