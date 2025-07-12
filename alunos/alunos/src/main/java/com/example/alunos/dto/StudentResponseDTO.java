package com.example.alunos.dto;

import java.time.LocalDate;
import java.util.List;

public record StudentResponseDTO(Long id,String name, String phoneNumber, LocalDate birthday, List<EnrollmentDTO> enrollments) {
}
