package com.example.people_management.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.people_management.Entity.User;
import com.example.people_management.dto.request.UserCreationRequest;
import com.example.people_management.dto.response.ApiRespone;
import com.example.people_management.service.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
       
    @PostMapping()
    ApiRespone<User> createUserRoleEmployee(@ModelAttribute UserCreationRequest request, @RequestParam("avatar") MultipartFile multipartFile) throws IOException {
        ApiRespone<User> apiRespone = new ApiRespone<>();      
        apiRespone.setResult(userService.createUserRoleEmployee(request, multipartFile));
        return apiRespone;
    }

    @GetMapping()
    List<User> getUserInfo() {
        return userService.getUserInfo();
    }

    @GetMapping("/{user_id}")
    User getUserInfoById(@PathVariable("user_id") long user_id) {
        return userService.getUserInfoById(user_id);
    }

    @PutMapping("/{user_id}")
    User updateUserById(@PathVariable("user_id") long user_id, @RequestBody UserCreationRequest request) {
        return userService.updateUserById(user_id, request);
    }

    @DeleteMapping("/{user_id}")
    String DeleteUserbyId(@PathVariable("user_id") long user_id) {
        userService.DeleteUserbyId(user_id);
        return "User has been deleted";
    }

    @PostMapping("/register")
    ApiRespone<User> CheckRegister(@ModelAttribute @Valid UserCreationRequest request, @RequestParam("avatar") MultipartFile multipartFile) throws IOException{
        ApiRespone<User> apiResponse = new ApiRespone<>();
        if (userService.CheckRegister(request)) {
            apiResponse.setMessage("username existed");
            return apiResponse;
        }
        apiResponse.setMessage("Register successfully");
        apiResponse.setResult(userService.createUserRoleEmployee(request, multipartFile));
        return apiResponse;
    }
}
