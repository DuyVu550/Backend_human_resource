package com.example.people_management.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.people_management.Entity.User;
import com.example.people_management.dto.request.UserCreationRequest;
import com.example.people_management.enums.Role;
import com.example.people_management.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRes;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public static String uploadDirectory = "D:/uploads/avatar";

    public User createUserRole(UserCreationRequest userRequest, MultipartFile multipartFile)
            throws IOException {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.setAddress(userRequest.getAddress());
        user.setRole(Role.Employee.name());
        String fileName = multipartFile.getOriginalFilename();
        Path path = Paths.get(uploadDirectory, fileName);
        Files.write(path, multipartFile.getBytes());
        user.setAvatar(fileName);
        return userRes.save(user);
    }

    @PreAuthorize("hasRole('HR')")
    public List<User> getUserInfo() {
        return userRes.findAll();
    }

    @PostAuthorize("returnObject.username == authentication.name or hasRole('HR')")
    public User getUserByName(String username) {
        return userRes.findByUsername(username).orElseThrow(() -> new RuntimeException("Not found user"));
    }

    @PostAuthorize("returnObject.username == authentication.name or hasRole('HR')")
    public User getUserInfoById(long user_id) {
        return userRes.findById(user_id).orElseThrow(() -> new RuntimeException("Not found user"));
    }

    @PostAuthorize("returnObject.username == authentication.name or hasRole('HR')")
    public User updateUserByName(String name, UserCreationRequest userRequest, MultipartFile multipartFile)
            throws IOException {
        User user = userRes.findByUsername(name).orElseThrow(() -> new RuntimeException("Not found user"));
        user.setUsername(userRequest.getUsername());
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.setAddress(userRequest.getAddress());
        String fileName = multipartFile.getOriginalFilename();
        Path path = Paths.get(uploadDirectory, fileName);
        Files.write(path, multipartFile.getBytes());
        user.setAvatar(fileName);
        return userRes.save(user);
    }

    @PreAuthorize("hasRole('HR')")
    public void DeleteUserbyId(long user_id) {
        userRes.deleteById(user_id);
    }

    @PreAuthorize("hasRole('HR')")
    public void DeleteUserbyName(String name) {
        userRes.deleteByUsername(name);
    }

    public boolean CheckRegister(UserCreationRequest userRequest) {
        return userRes.existsByUsername(userRequest.getUsername());
    }
}
