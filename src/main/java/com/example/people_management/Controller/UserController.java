package com.example.people_management.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.people_management.Entity.User;
import com.example.people_management.dto.request.UserCreationRequest;
import com.example.people_management.dto.request.UserUpdatesProfile;
import com.example.people_management.dto.response.ApiRespone;
import com.example.people_management.service.AuthenticationService;
import com.example.people_management.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping()
    List<User> getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.warn(auth.getName());
        log.warn(auth.getAuthorities().toString());
        return userService.getUserInfo();
    }

    @GetMapping("/id/{user_id}")
    User getUserInfoById(@PathVariable("user_id") long user_id) {
        return userService.getUserInfoById(user_id);
    }

    @GetMapping("/{name}")
    User getUserInfoByName(@PathVariable("name") String name) {
        return userService.getUserByName(name);
    }

    @PutMapping("/{name}")
    ApiRespone<User> updateUser(@PathVariable("name") String name,
            @ModelAttribute @Valid UserUpdatesProfile userRequest,
            @RequestParam(value = "avatar", required = false) MultipartFile multipartFile) throws IOException {
        ApiRespone<User> apiResponse = new ApiRespone<>();
        User user = userService.updateUserByName(name, userRequest, multipartFile);
        apiResponse.setResult(user);
        String token = authenticationService.generateToken(user);
        apiResponse.setToken(token);
        return apiResponse;
    }

    @DeleteMapping("/{user_id}")
    String DeleteUserbyId(@PathVariable("user_id") long user_id) {
        userService.DeleteUserbyId(user_id);
        return "User has been deleted";
    }

    @PostMapping("/register")
    ApiRespone<User> CheckRegister(@ModelAttribute @Valid UserCreationRequest request,
            @RequestParam(value = "avatar", required = false) MultipartFile multipartFile) throws IOException {
        ApiRespone<User> apiResponse = new ApiRespone<>();
        if (userService.CheckRegister(request)) {
            throw new RuntimeException("Username existed");
        }
        apiResponse.setMessage("Register successfully");
        User user = userService.createUserRole(request, multipartFile);
        apiResponse.setResult(user);
        String token = authenticationService.generateToken(user);
        apiResponse.setToken(token);
        return apiResponse;
    }
}
