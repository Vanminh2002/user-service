package com.example.userservice.permisson.permission.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionResponse {
    private Long id;
    private String name;
    private String description;
}
