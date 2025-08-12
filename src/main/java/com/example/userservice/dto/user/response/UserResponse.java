package com.example.userservice.dto.user.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;
    String fullName;
    String firstName;
    String email;
    String username;
    String password;
    String image;
    String address;
    String phone;
    List<String> roleId;
}
