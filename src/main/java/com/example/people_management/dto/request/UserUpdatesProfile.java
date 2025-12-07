package com.example.people_management.dto.request;

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
    private String name;
    private Integer age;
    private int department_id;
    private String address;
    private String salary_rate;
}
