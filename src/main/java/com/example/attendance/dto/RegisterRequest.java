package com.example.attendance.dto;

import com.example.attendance.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private Role role;
    // Additional fields for student/teacher profile can be added here or handled
    // separately
    private String firstName;
    private String lastName;
}
