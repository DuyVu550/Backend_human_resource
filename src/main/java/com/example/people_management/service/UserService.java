package com.example.people_management.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.people_management.Entity.User;
import com.example.people_management.dto.request.UserCreationRequest;
import com.example.people_management.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRes;
    public static String uploadDirectory = "D:/uploads/avatar";

    public User createUserRole(UserCreationRequest userRequest, MultipartFile multipartFile)
            throws IOException {
        if (userRes.existsByUsername(userRequest.getUsername()))
            throw new RuntimeException("Existed");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.setAddress(userRequest.getAddress());
        user.setRole(userRequest.getRole());
        String fileName = multipartFile.getOriginalFilename();
        Path path = Paths.get(uploadDirectory, fileName);
        Files.write(path, multipartFile.getBytes());
        user.setAvatar(fileName);
        return userRes.save(user);
    }

    public List<User> getUserInfo() {
        return userRes.findAll();
    }

    public User getUserInfoById(long user_id) {
        return userRes.findById(user_id).orElseThrow(() -> new RuntimeException("Not found user"));
    }

    public User updateUserByName(String name, UserCreationRequest userRequest) {
        User user = userRes.findByUsername(name).orElseThrow(() -> new RuntimeException("Not found user"));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.setAddress(userRequest.getAddress());
        user.setDepartment_id(userRequest.getDepartment_id());
        user.setSalary_rate(Double.parseDouble(userRequest.getSalary_rate()));
        return userRes.save(user);
    }

    public void DeleteUserbyId(long user_id) {
        userRes.deleteById(user_id);
    }

    public boolean CheckRegister(UserCreationRequest userRequest) {
        return userRes.existsByUsername(userRequest.getUsername());
    }
}
