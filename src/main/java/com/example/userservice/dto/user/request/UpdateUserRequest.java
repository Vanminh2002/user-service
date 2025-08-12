package com.example.userservice.dto.user.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UpdateUserRequest {
    String username;
    String password;


}
