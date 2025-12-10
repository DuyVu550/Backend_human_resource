package com.example.people_management.dto.request;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequest {

    @NotBlank(message = "username not null")
    @Column(unique = true)
    private String username;
    @Size(min = 8, message = "Password must not under 8 characters")
    private String password;
    private String repassword;
    @NotBlank(message = "Email not null")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "name not null")
    private String name;
    @NotNull(message = "dob not null")
    private LocalDate dob;
    private int department_id;
    @NotBlank(message = "address not null")
    private String address;
    private String salary_rate;
    // private String avatar;

    // public String getUsername() {
    // return username;
    // }

    // public void setUsername(String username) {
    // this.username = username;
    // }

    // public String getPassword() {
    // return password;
    // }

    // public void setPassword(String password) {
    // this.password = password;
    // }

    // public String getRole() {
    // return role;
    // }

    // public void setRole(String role) {
    // if (!role.equals("Employee") || !role.equals("HR")) {
    // this.role = "HR";
    // }
    // this.role = role;
    // }
}
