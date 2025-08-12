//package com.example.userservice.dto;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class ApiResponse<T> {
//    private int code = 200;
//    private String message;
//    private T data;
//
//
//    public static <T> ApiResponse<T> success(T data) {
//        return new ApiResponse<>(200, "Success", data);
//    }
//
//    public static <T> ApiResponse<T> error(int code, String message) {
//        return new ApiResponse<>(code, message, null);
//    }
//}
