package com.example.userservice.controller;


import com.example.userservice.dto.user.request.UpdateUserRequest;
import com.example.userservice.dto.user.request.UserCreateRequest;
import com.example.userservice.dto.user.response.UserResponse;
import com.example.userservice.services.UserService;
import jakarta.annotation.Resource;

import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    @Resource
    private UserService userService;


    @PostMapping("create")
    ApiResponse<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        UserResponse response = userService.createUser(request);
        return ApiResponse.success(response);

    }


    @PutMapping("update/{id}")
    ApiResponse<UserResponse> update(@Valid @PathVariable Long id, @RequestBody UpdateUserRequest request) {
        UserResponse response = userService.update(id, request);
        return ApiResponse.success(response);
    }

    @GetMapping("get-by-id/{id}")
    ApiResponse<UserResponse> getById(@PathVariable Long id) {
        UserResponse response = userService.getById(id);
        return ApiResponse.success(response);
    }

    @GetMapping("get-all")
    ApiResponse<List<UserResponse>> getAll() {
        List<UserResponse> response = userService.getAll();
        return ApiResponse.success(response);
    }

    @DeleteMapping("delete/{id}")
    ApiResponse<String> getAll(@PathVariable Long id) {
        userService.deleteUser(id);
//        return ApiResponse.success();
        return ApiResponse.<String>builder()
                .message("Xóa người dùng thành công")
                .build();
    }


    @GetMapping("get-myInfo")
    ApiResponse<UserResponse> getMyInfo() {
        UserResponse response = userService.getMyInfo();
        return ApiResponse.success(response);
    }
}
