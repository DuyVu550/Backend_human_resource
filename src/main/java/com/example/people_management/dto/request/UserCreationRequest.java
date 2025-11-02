package com.example.people_management.dto.request;

public class UserCreationRequest {

    private String username;
    private String password;
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (!role.equals("Employee") || !role.equals("HR")) {
            this.role = "HR";
        }
        this.role = role;
    }
}
