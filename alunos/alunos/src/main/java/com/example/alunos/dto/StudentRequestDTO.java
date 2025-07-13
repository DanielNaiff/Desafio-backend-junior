package com.example.alunos.dto;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public record StudentRequestDTO(
        String name,
        String phoneNumber,
        LocalDate birthday,
        @NotNull List<@Valid EnrollmentDTO> enrollments) {


}
