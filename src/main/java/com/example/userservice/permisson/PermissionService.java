package com.example.userservice.permisson;

import com.example.userservice.permisson.permission.request.CreatePermissionRequest;
import com.example.userservice.permisson.permission.response.PermissionResponse;
import com.example.userservice.exception.AppException;
import com.example.userservice.exception.ErrorCode;
import com.example.userservice.role.Role;
import com.example.userservice.role.role.response.RoleResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PermissionService {

    @Resource
    private PermissionRepository permissionRepository;

    @Resource
    PermissionMapper permissionMapper;

    public PermissionResponse createPermission(CreatePermissionRequest request) {
        if (permissionRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.PERMISSION_EXISTS);
        }

        var permission = permissionMapper.toEntity(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toResponse(permission);
    }

    public List<PermissionResponse> getAll() {
        var permission = permissionRepository.findAll();
        if (permission.isEmpty()) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        return permission.stream().map(permissionMapper::toResponse).collect(Collectors.toList());
    }


    public PermissionResponse getById(Long id) {
        var permission = permissionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        return permissionMapper.toResponse(permission);
    }


    public void deletePermission(Long id) {
        var permission = getById(id);

        permissionRepository.deleteById(id);
    }


    public PermissionResponse getPermissionById(Long id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        return permissionMapper.toResponse(permission);

    }
}
