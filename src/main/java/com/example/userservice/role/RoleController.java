package com.example.userservice.role;

import com.example.userservice.role.role.request.CreateRoleRequest;
import com.example.userservice.role.role.response.RoleResponse;
import jakarta.annotation.Resource;

import org.example.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RoleController {
    @Resource
    private RoleService roleService;


    @PostMapping("create")
    ApiResponse<RoleResponse> createRole(@RequestBody CreateRoleRequest request) {
        RoleResponse response = roleService.createRole(request);
        return ApiResponse.success(response);
    }


    @GetMapping("get-all")
    ApiResponse<List<RoleResponse>> getAll() {
        List<RoleResponse> response = roleService.getAll();
        return ApiResponse.success(response);
    }


    @DeleteMapping("delete/{id}")
    ApiResponse<String> deleteRole(@PathVariable Long id) {
        roleService.deleteRoleById(id);
        return ApiResponse.<String>builder()
                .message("Xóa Role thành công")
                .build();
    }

    @GetMapping("get-by/{id}")
    ApiResponse<List<RoleResponse>> getRoleById(@PathVariable List<Long> id) {
        List<RoleResponse> response = roleService.getAllRoleWithPermission(id);
        return ApiResponse.success(response);
    }

    @PutMapping("update-by/{id}")
    ApiResponse<RoleResponse> updateRole(@PathVariable Long id, @RequestBody CreateRoleRequest request) {
        RoleResponse response = roleService.updateRole(id, request);
        return ApiResponse.success(response);
    }
}
