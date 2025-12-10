package com.example.people_management.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.people_management.Entity.User;
import com.example.people_management.dto.request.UserCreationRequest;
import com.example.people_management.dto.request.UserUpdatesProfile;
import com.example.people_management.enums.Role;
import com.example.people_management.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRes;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Cloudinary cloudinary;
    @Value("${app.upload-dir}")
    public String uploadDirectory;

    public User createUserRole(UserCreationRequest userRequest, MultipartFile multipartFile)
            throws IOException {
        User user = new User();
        if (!userRequest.getPassword().equals(userRequest.getRepassword())) {
            throw new RuntimeException("Password not match");
        }
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setDob(userRequest.getDob());
        user.setAddress(userRequest.getAddress());
        user.setRole(Role.Employee.name());
        if (!multipartFile.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(),
                    ObjectUtils.emptyMap());
            String imageUrl = uploadResult.get("secure_url").toString();
            user.setAvatar(imageUrl);
        } else {
            user.setAvatar(null);
        }
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
    public User updateUserByName(String name, UserUpdatesProfile userRequest, MultipartFile multipartFile)
            throws IOException {
        User user = userRes.findByUsername(name).orElseThrow(() -> new RuntimeException("Not found user"));
        if (!user.getUsername().equals(userRequest.getUsername())) {
            if (userRes.existsByUsername(userRequest.getUsername())) {
                throw new RuntimeException("Username existed");
            }
        }
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setDob(userRequest.getDob());
        user.setAddress(userRequest.getAddress());
        if (!multipartFile.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(),
                    ObjectUtils.emptyMap());
            String imageUrl = uploadResult.get("secure_url").toString();
            user.setAvatar(imageUrl);
        } else {
            user.setAvatar(null);
        }
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

    public boolean CheckExistUsername(UserUpdatesProfile userRequest) {
        return userRes.existsByUsername(userRequest.getUsername());
    }

}
