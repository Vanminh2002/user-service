package com.example.userservice.rolePermission;

import com.example.userservice.role.RoleMapper;
import com.example.userservice.permisson.PermissionRepository;
import com.example.userservice.role.RoleRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleRepository roleRepository;
    @Resource
    private RolePermissionRepository rolePermissionRepository;


    @Resource
    private PermissionRepository permissionRepository;
    public void assignPermissionToRole(Long roleId, Long permissionId) {
//         Kiểm tra tồn tại
////        if (!roleRepository.existsById(roleId) || !permissionRepository.existsById(permissionId)) {
////            throw new AppException(ErrorCode.NOT_FOUND);
//        }

        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        rolePermissionRepository.save(rolePermission);
    }
}
