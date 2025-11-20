package com.example.people_management.Controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.people_management.dto.request.AuthenticationRequest;
import com.example.people_management.dto.request.IntrospectRequest;
import com.example.people_management.dto.response.ApiRespone;
import com.example.people_management.dto.response.AuthenticationResponse;
import com.example.people_management.dto.response.IntrospectResponse;
import com.example.people_management.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiRespone<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        var result = authenticationService.authenticate(authenticationRequest);
        return ApiRespone.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiRespone<IntrospectResponse> introspect(@RequestBody IntrospectRequest introspectRequest)
            throws JOSEException, ParseException {
        var result = authenticationService.introspect(introspectRequest);
        return ApiRespone.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

}
