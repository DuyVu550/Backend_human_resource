package com.example.people_management.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeCreationRequest {
    @NotBlank
    private String name;
    @NotBlank
    @Min(value = 18)
    private int age;
    @NotBlank
    private String address;
    @NotBlank
    private int departments_id;
    private String avatar;
    @NotBlank
    private double salary_rate;
}
