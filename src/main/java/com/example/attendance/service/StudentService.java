package com.example.attendance.service;

import com.example.attendance.dto.StudentDto;
import com.example.attendance.model.Student;
import com.example.attendance.model.User;
import com.example.attendance.repository.StudentRepository;
import com.example.attendance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return mapToDto(student);
    }

    @Transactional
    public StudentDto createStudent(StudentDto dto) {
        // Typically create user first or link to existing user
        // For simplicity, assuming user is created separately or handled here
        // This is a minimal implementation
        Student student = Student.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .rollNo(dto.getRollNo())
                .phone(dto.getPhone())
                .build();

        // If email is provided, maybe link to user?
        if (dto.getEmail() != null) {
            User user = userRepository.findByEmail(dto.getEmail()).orElse(null);
            student.setUser(user);
        }

        Student saved = studentRepository.save(student);
        return mapToDto(saved);
    }

    private StudentDto mapToDto(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .rollNo(student.getRollNo())
                .email(student.getUser() != null ? student.getUser().getEmail() : null)
                .phone(student.getPhone())
                .build();
    }
}
