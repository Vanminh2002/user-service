package com.example.userservice.permisson;


import com.example.userservice.permisson.permission.request.CreatePermissionRequest;
import com.example.userservice.permisson.permission.response.PermissionResponse;
import com.example.userservice.role.role.response.RoleResponse;
import jakarta.annotation.Resource;
import org.example.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("permissions")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @PostMapping("create")
    ApiResponse<PermissionResponse> create(@RequestBody CreatePermissionRequest request) {
        PermissionResponse response = permissionService.createPermission(request);
        if (response != null) {
            return ApiResponse.success(response);
        }
        return ApiResponse.error(500, "Lỗi khi tạo quyền");
    }

    @GetMapping("get-by/{id}")
    ApiResponse<PermissionResponse> getPermissionById(@PathVariable Long id) {
        PermissionResponse response = permissionService.getPermissionById(id);
        return ApiResponse.success(response);
    }
}
