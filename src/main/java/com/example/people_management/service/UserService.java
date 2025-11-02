package com.example.people_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.people_management.Entity.User;
import com.example.people_management.dto.request.UserCreationRequest;
import com.example.people_management.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRes;

    public User createUser(UserCreationRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        return userRes.save(user);
    }

    public List<User> getUserInfo() {
        return userRes.findAll();
    }

    public User getUserInfoById(long user_id) {
        return userRes.findById(user_id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public User updateUserById(long user_id, UserCreationRequest userRequest) {
        User user = getUserInfoById(user_id);
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        return userRes.save(user);
    }

    public void DeleteUserbyId(long user_id) {
        userRes.deleteById(user_id);
    }

    public boolean CheckLogin(UserCreationRequest userRequest, String password) {
        List<User> userList = getUserInfo();
        for (User user : userList) {
            if (user.getPassword().equals(password)
                    && user.getUsername().equals(userRequest.getUsername())) {
                return true;
            }
        }
        return false;
    }
}
