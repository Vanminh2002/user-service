package com.example.userservice.permisson.permission.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePermissionRequest {
    private String name;
    private String description;
}
