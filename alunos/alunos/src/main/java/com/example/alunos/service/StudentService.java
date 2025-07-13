package com.example.alunos.service;

import com.example.alunos.dto.EnrollmentDTO;
import com.example.alunos.dto.StudentRequestDTO;
import com.example.alunos.dto.StudentResponseDTO;
import com.example.alunos.mapper.StudentMapper;
import com.example.alunos.model.Enrollment;
import com.example.alunos.model.Student;
import com.example.alunos.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentResponseDTO save(StudentRequestDTO requestDTO){
        Student student = studentMapper.toEntity(requestDTO);
        studentRepository.save(student);

        return studentMapper.toResponse(student);
    }

    public List<StudentResponseDTO> findAllStudents(){
        return studentRepository.findAll().stream().map(studentMapper::toResponse).toList();
    }

    public List<EnrollmentDTO> findAllEnrollmentsByStudentId(Long id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno nao encontrado"));
        return student.getEnrollments().stream().map(m -> new EnrollmentDTO(m.getEnrollmentCode(), m.getCourseName(), m.getBeginingDate())).toList();
    }

    public void deleteStudent(Long id){
        if(!studentRepository.existsById(id)){
            throw  new EntityNotFoundException("Aluno nao existe");
        }
        studentRepository.deleteById(id);
    }

    public StudentResponseDTO put(Long id,StudentRequestDTO requestDTO){
        Student s = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno nao encontrado"));
        s.setName(requestDTO.name());
        s.setPhoneNumber(requestDTO.phoneNumber());
        s.setBirthday(requestDTO.birthday());
        for (EnrollmentDTO e: requestDTO.enrollments()){
            Enrollment enrollment = new Enrollment();
            enrollment.setCourseName(e.courseName());
            enrollment.setBeginingDate(e.beginingDate());
            s.getEnrollments().add(enrollment);
        }

        return studentMapper.toResponse(studentRepository.save(s));
    }
}
