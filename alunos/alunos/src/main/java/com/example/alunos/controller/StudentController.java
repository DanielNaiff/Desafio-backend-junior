package com.example.alunos.controller;

import com.example.alunos.dto.EnrollmentDTO;
import com.example.alunos.dto.StudentRequestDTO;
import com.example.alunos.dto.StudentResponseDTO;
import com.example.alunos.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponseDTO> create(@Valid @RequestBody StudentRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(requestDTO));
    }

    @GetMapping
    public List<StudentResponseDTO> listAll(){

        return studentService.findAllStudents();
    }

    @GetMapping("/{id}/matriculas")
    public List<EnrollmentDTO> listByEnrollments(@PathVariable Long id){
        return studentService.findAllEnrollmentsByStudentId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> update(@PathVariable Long id, @RequestBody StudentRequestDTO requestDTO){
        return ResponseEntity.ok(studentService.put(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
