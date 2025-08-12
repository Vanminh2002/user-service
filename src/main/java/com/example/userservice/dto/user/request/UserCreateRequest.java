package com.example.userservice.dto.user.request;

import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    String fullName;
    String firstName;
    String email;
    @Pattern(
            regexp = "^[A-Za-z\\d]{3,}$",
            message = "USERNAME_INVALID"
    )
    String username;
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&])[A-Za-z\\d!@#$%^&]{3,}$",
            message = "PASSWORD_INVALID"
    )
    String password;
    String image;
    String address;
    String phone;
    List<Long> roleId;
}
