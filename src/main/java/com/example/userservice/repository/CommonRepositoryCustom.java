package com.example.userservice.repository;

import com.example.userservice.role.role.request.CreateRoleRequest;
import com.example.userservice.role.role.response.RoleResponse;
import com.example.userservice.role.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommonRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

//    public RoleResponse createRoleWithPermission(CreateRoleRequest request) {
//        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("create_role_with_permission");
//
//        query.registerStoredProcedureParameter("role_name", String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter("permission_name", String[].class, ParameterMode.IN);
//        query.registerStoredProcedureParameter("description", String.class, ParameterMode.IN);
//
//        query.setParameter("role_name", request.getName());
//        query.setParameter("permission_name", request.getPermission().toArray(new String[0]));
//        query.setParameter("description", request.getDescription());
//
//        query.execute();
//
//
//        Role role = entityManager.createQuery("select r from  Role r where  r.name = :name", Role.class)
//                .setParameter("name", request.getName())
//                .getSingleResult();
//
//        List<String> permissions = entityManager.createQuery("select p.name from Permission p join RolePermission rp on p.id = rp.permissionId where  rp.roleId = :roleId",
//                        String.class
//                )
//                .setParameter("roleId", role.getId())
//                .getResultList();
//        RoleResponse response = new RoleResponse().builder()
//                .id(role.getId())
//                .name(role.getName())
//                .description(role.getDescription())
//                .permissions(permissions)
//                .build();
//        return response;
//    }





}
