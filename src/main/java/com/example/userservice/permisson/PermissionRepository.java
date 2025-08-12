package com.example.userservice.permisson;

import com.example.userservice.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    boolean existsByName(String name);
    List<Permission> findAllById(Iterable<Long> ids);

   Permission findByName(String name);


    @Query(value = """
        SELECT p.* 
        FROM permissions p
        JOIN roles_permission rp ON rp.permission_id = p.id
        WHERE rp.role_id = :roleId
    """, nativeQuery = true)
    List<Permission> findPermissionsByRoleId(@Param("roleId") Long roleId);



}
