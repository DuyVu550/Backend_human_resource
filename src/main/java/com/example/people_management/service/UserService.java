package com.example.people_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.people_management.Entity.User;
import com.example.people_management.dto.request.UserCreationRequest;
import com.example.people_management.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRes;

    public User createUser(UserCreationRequest userRequest) {
        if (userRes.existsByUsername(userRequest.getUsername()))
            throw new RuntimeException("Existed");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());
        user.setName(userRequest.getUsername());
        user.setAge(userRequest.getAge());
        user.setAddress(userRequest.getAddress());
        user.setDepartments_id(userRequest.getDepartments_id());
        user.setAvatar(userRequest.getAvatar());
        user.setSalary_rate(userRequest.getSalary_rate());
        return userRes.save(user);
    }

    public List<User> getUserInfo() {
        return userRes.findAll();
    }

    public User getUserInfoById(long user_id) {
        return userRes.findById(user_id).orElseThrow(() -> new RuntimeException("Not found user"));
    }

    public User updateUserById(long user_id, UserCreationRequest userRequest) {
        User user = getUserInfoById(user_id);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());
        user.setName(userRequest.getUsername());
        user.setAge(userRequest.getAge());
        user.setAddress(userRequest.getAddress());
        user.setDepartments_id(userRequest.getDepartments_id());
        user.setAvatar(userRequest.getAvatar());
        user.setSalary_rate(userRequest.getSalary_rate());
        return userRes.save(user);
    }

    public void DeleteUserbyId(long user_id) {
        userRes.deleteById(user_id);
    }

    public boolean CheckRegister(UserCreationRequest userRequest) {
        return userRes.existsByUsername(userRequest.getUsername());
    }
}
