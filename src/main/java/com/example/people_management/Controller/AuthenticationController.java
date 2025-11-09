package com.example.people_management.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.people_management.dto.request.ApiRespone;
import com.example.people_management.dto.request.AuthenticationRequest;
import com.example.people_management.dto.response.AuthenticationResponse;
import com.example.people_management.service.AuthenticationService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiRespone<AuthenticationResponse> authenticated(@RequestBody AuthenticationRequest authenticationRequest) {
        boolean result = authenticationService.authenticated(authenticationRequest);
        return ApiRespone.<AuthenticationResponse>builder()
                .result(
                        AuthenticationResponse.builder().authenticated(result).build())
                .build();
    }

}
