package com.example.people_management.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdatesProfile {
    private String username;
    private String email;
    private String name;
    private LocalDate dob;
    private int department_id;
    private String address;
    private String salary_rate;
}
