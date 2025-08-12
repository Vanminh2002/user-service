package com.example.userservice.mapper;

import com.example.userservice.dto.user.request.UpdateUserRequest;
import com.example.userservice.dto.user.request.UserCreateRequest;
import com.example.userservice.dto.user.response.UserResponse;
import com.example.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toDto(UserCreateRequest request);

    UserResponse toResponse(User user);

    void toUpdateUser(@MappingTarget User user, UpdateUserRequest request);
}
