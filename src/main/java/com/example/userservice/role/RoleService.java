package com.example.userservice.role;

import com.example.userservice.permisson.Permission;
import com.example.userservice.permisson.permission.response.PermissionResponse;
import com.example.userservice.role.role.request.CreateRoleRequest;
import com.example.userservice.role.role.response.RoleResponse;
import com.example.userservice.exception.AppException;
import com.example.userservice.exception.ErrorCode;
import com.example.userservice.permisson.PermissionMapper;
import com.example.userservice.repository.CommonRepositoryCustom;
import com.example.userservice.permisson.PermissionRepository;
import com.example.userservice.rolePermission.RolePermission;
import com.example.userservice.rolePermission.RolePermissionRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private PermissionRepository permissionRepository;

    @Resource
    private RolePermissionRepository rolePermissionRepository;

    @Resource
    private CommonRepositoryCustom commonRepositoryCustom;

    public RoleResponse createRole(CreateRoleRequest request) {
//        return commonRepositoryCustom.createRoleWithPermission(request);
        if (roleRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        if (permissionRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        Role role = roleMapper.toDto(request);


        Set<Permission> permissions = new HashSet<>();
        for (Long permissionId : request.getPermissionId()) {
            Permission permission = permissionRepository.findById(permissionId)
                    .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
            permissions.add(permission);
        }
        role.setPermissionId(permissions.stream().map(Permission::getId).collect(Collectors.toSet()));
        roleRepository.save(role);
        var roleId = role.getId();

        for (Permission permission : permissions) {
            RolePermission rp = new RolePermission();
            rp.setRoleId(roleId);
            rp.setPermissionId(permission.getId());
            rolePermissionRepository.save(rp);
        }

        return roleMapper.toResponse(role, new ArrayList<>(permissions));
    }

    public List<RoleResponse> getAll() {
        List<Role> listRole = roleRepository.findAll();
        if (listRole.isEmpty()) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        List<RoleResponse> responseList = new ArrayList<>();

        for (Role role : listRole) {
            List<Permission> permissions = permissionRepository.findPermissionsByRoleId(role.getId());
            RoleResponse response = roleMapper.toResponse(role, permissions);
            responseList.add(response);
        }

        return responseList;
    }


    public void deleteRoleById(Long id) {
        var role = roleRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        roleRepository.deleteById(id);
    }

    public List<RoleResponse> getAllRoleWithPermission(List<Long> roleId) {
        List<Role> listRole = roleRepository.findAllById(roleId);

        // lấy permission từ permissionId của role
        List<Long> permission = listRole
                .stream()
                .filter(role -> role.getPermissionId() != null)
                .flatMap(r -> r.getPermissionId().stream())
                .distinct()
                .toList();

        Map<Long, Permission> permissionMap = permissionRepository.findAllById(permission)
                .stream()
                .collect(Collectors.toMap(Permission::getId, p -> p));
        return listRole.stream()
                .map(role -> RoleResponse.builder()
                        .id(role.getId())
                        .name(role.getName())
                        .description(role.getDescription())
                        .permissions(
                                role.getPermissionId() == null ? List.of() : role.getPermissionId().stream()
                                        .map(permissionMap::get)
                                        .filter(Objects::nonNull)
                                        .map(Permission::getName) // trả về tên => List<String>
                                        .toList()
                        )

                        .build()).toList();
    }

    public RoleResponse updateRole(Long id, CreateRoleRequest request) {
        try {
            Role role = roleRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

            roleMapper.toUpdateRole(role, request);

            Set<Permission> permissions = request.getPermissionId()
                    .stream()
                    .map(pId -> permissionRepository.findById(pId)
                            .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND)))
                    .collect(Collectors.toSet());

            Set<Long> oldPermissionIds = rolePermissionRepository.findPermissionIdsByRoleId(role.getId());
            Set<Long> newPermission = permissions.stream().map(Permission::getId).collect(Collectors.toSet());


            if (!oldPermissionIds.isEmpty() && oldPermissionIds.equals(newPermission)) {
                throw new RuntimeException("Quyền không thay đổi");
            }

            rolePermissionRepository.deleteByRoleId(role.getId());

            role.setPermissionId(newPermission);
            roleRepository.save(role);


            for (Permission permission : permissions) {
                RolePermission rp = new RolePermission();
                rp.setRoleId(role.getId());
                rp.setPermissionId(permission.getId());
                rolePermissionRepository.save(rp);
            }
            return roleMapper.toResponse(role, new ArrayList<>(permissions));
        } catch (Exception e) {
            log.warn("err" + e.getMessage());
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }
}
