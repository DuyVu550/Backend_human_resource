package com.example.people_management.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @Column(unique = true)
    private String username;
    @Size(min = 8, message = "Password must not under 8 characters")
    private String password;
    @NotBlank
    @Column(unique = true)
    private String name;
    @Min(value = 18)
    private int age;
    private int department_id;
    @NotBlank
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
