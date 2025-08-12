package com.example.userservice.controller;

import com.example.userservice.dto.auth.request.AuthenticationRequest;
import com.example.userservice.dto.auth.request.IntrospectRequest;
import com.example.userservice.dto.auth.request.LogoutRequest;
import com.example.userservice.dto.auth.response.AuthenticationResponse;
import org.example.dto.ApiResponse;
import com.example.userservice.dto.auth.response.IntrospectResponse;
import com.example.userservice.services.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Resource
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authentication(request);
        return ApiResponse.success(result);
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.success(result);
    }

    @PostMapping("logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .message("Logout successful")
                .build();
    }
}
