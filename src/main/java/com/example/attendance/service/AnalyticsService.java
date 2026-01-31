package com.example.attendance.service;

import com.example.attendance.model.Attendance;
import com.example.attendance.model.AttendanceStatus;
import com.example.attendance.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final AttendanceRepository attendanceRepository;

    public Map<String, Object> getStudentAttendanceAnalytics(Long studentId) {
        List<Attendance> records = attendanceRepository.findByStudentId(studentId);
        int total = records.size();
        long present = records.stream()
                .filter(a -> a.getStatus() == AttendanceStatus.PRESENT)
                .count();
        long late = records.stream()
                .filter(a -> a.getStatus() == AttendanceStatus.LATE)
                .count();

        // Count late as present or half? Assuming present for now or strict.
        // Let's assume late counts as present for percentage but tracked separately

        double percentage = total == 0 ? 0 : ((double) (present + late) / total) * 100;

        Map<String, Object> response = new HashMap<>();
        response.put("totalClasses", total);
        response.put("present", present);
        response.put("late", late);
        response.put("absent", total - (present + late));
        response.put("attendancePercentage", Math.round(percentage * 100.0) / 100.0);

        return response;
    }
}
