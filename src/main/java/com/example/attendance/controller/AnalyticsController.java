package com.example.attendance.controller;

import com.example.attendance.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Map<String, Object>> getStudentAnalytics(@PathVariable Long studentId) {
        return ResponseEntity.ok(analyticsService.getStudentAttendanceAnalytics(studentId));
    }
}
