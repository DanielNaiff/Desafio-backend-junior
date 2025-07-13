package com.example.alunos.mapper;

import com.example.alunos.dto.EnrollmentDTO;
import com.example.alunos.dto.StudentRequestDTO;
import com.example.alunos.dto.StudentResponseDTO;
import com.example.alunos.model.Enrollment;
import com.example.alunos.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentMapper {
    public Student toEntity(StudentRequestDTO requestDTO){
        Student student = new Student();

        student.setName(requestDTO.name());
        student.setBirthday(requestDTO.birthday());
        student.setPhoneNumber(requestDTO.phoneNumber());
        List<Enrollment> enrollments = requestDTO.enrollments().stream().map(m->{
            Enrollment enrollment = new Enrollment();
            enrollment.setEnrollmentCode(m.enrollmentCode());
            enrollment.setBeginingDate(m.beginingDate());
            enrollment.setCourseName(m.courseName());
            enrollment.setStudent(student);
            return enrollment;
        }).toList();
        student.setEnrollments(enrollments);

        return student;
    }

    public StudentResponseDTO toResponse(Student student){
        List<EnrollmentDTO> enrollmentDTOS = student.getEnrollments().stream().map(m -> new EnrollmentDTO(m.getEnrollmentCode(), m.getCourseName(), m.getBeginingDate())).toList();

        return new StudentResponseDTO(student.getId(), student.getName(), student.getPhoneNumber(), student.getBirthday(), enrollmentDTOS);
    }
}
