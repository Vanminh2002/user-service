package com.example.userservice.rolePermission;

import com.example.userservice.permisson.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

    void deleteByRoleId(Long id);

    @Query(value = "SELECT permission_id FROM roles_permission WHERE role_id = :roleId", nativeQuery = true)
    Set<Long> findPermissionIdsByRoleId(Long roleId);

}
