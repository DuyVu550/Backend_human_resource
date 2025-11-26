package com.example.people_management.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequestByAdmin {
    @NotBlank
    @Column(unique = true)
    private String username;
    @Size(min = 8, message = "Password must not under 8 characters")
    private String password;
    @NotBlank
    private String role;
    @NotBlank
    @Column(unique = true)
    private String name;
    @Min(value = 18)
    private int age;
    @NotBlank
    private String address;
    @NotBlank
    private int departments_id;
    @NotNull
    @Positive
    private double salary_rate;
    // private String avatar;
}
