package com.example.attendance.service;

import com.example.attendance.dto.AttendanceDto;
import com.example.attendance.model.Attendance;
import com.example.attendance.model.Course;
import com.example.attendance.model.Student;
import com.example.attendance.repository.AttendanceRepository;
import com.example.attendance.repository.CourseRepository;
import com.example.attendance.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EmailService emailService;

    public AttendanceDto markAttendance(AttendanceDto dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Attendance attendance = Attendance.builder()
                .student(student)
                .course(course)
                .date(dto.getDate())
                .status(com.example.attendance.model.AttendanceStatus.valueOf(dto.getStatus()))
                .remarks(dto.getRemarks())
                .build();

        Attendance saved = attendanceRepository.save(attendance);

        // Send email if Absent
        if (saved.getStatus() == com.example.attendance.model.AttendanceStatus.ABSENT && student.getUser() != null) {
            emailService.sendEmail(
                    student.getUser().getEmail(),
                    "Attendance Alert: Absent",
                    "Dear " + student.getFirstName() + ",\n\nYou have been marked ABSENT for " + course.getName()
                            + " on " + saved.getDate() + ".\n\nRegards,\nAttendance System");
        }

        return mapToDto(saved);
    }

    public List<AttendanceDto> getAttendanceByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<AttendanceDto> getAttendanceByCourse(Long courseId) {
        return attendanceRepository.findByCourseId(courseId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private AttendanceDto mapToDto(Attendance attendance) {
        return AttendanceDto.builder()
                .id(attendance.getId())
                .studentId(attendance.getStudent().getId())
                .studentName(attendance.getStudent().getFirstName() + " " + attendance.getStudent().getLastName())
                .courseId(attendance.getCourse().getId())
                .courseName(attendance.getCourse().getName())
                .date(attendance.getDate())
                .status(attendance.getStatus().name())
                .remarks(attendance.getRemarks())
                .build();
    }
}
