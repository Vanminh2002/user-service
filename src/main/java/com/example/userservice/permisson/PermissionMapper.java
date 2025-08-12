package com.example.userservice.permisson;

import com.example.userservice.permisson.permission.request.CreatePermissionRequest;
import com.example.userservice.permisson.permission.response.PermissionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
//    @Mapping(source = "description",target = "description")
    Permission toEntity(CreatePermissionRequest request);

    PermissionResponse toResponse(Permission permission);

    void toUpdatePermission(@MappingTarget Permission permission, CreatePermissionRequest request);
}
