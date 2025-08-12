package com.example.userservice.role;

import com.example.userservice.role.role.request.CreateRoleRequest;
import com.example.userservice.role.role.response.RoleResponse;
import com.example.userservice.permisson.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toDto(CreateRoleRequest request);

//    RoleResponse toResponse(Role role);
    default RoleResponse toResponse(Role role, List<Permission> permission) {
        return RoleResponse.builder()
                .id(role.getId())
                .description(role.getDescription())
                .name(role.getName())
                .permissions(permission.stream().map(Permission::getName).collect(Collectors.toList()))
                .build();
    }



    void toUpdateRole(@MappingTarget Role role, CreateRoleRequest request);
}
