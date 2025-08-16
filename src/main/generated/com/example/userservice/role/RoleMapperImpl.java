package com.example.userservice.role;

import com.example.userservice.role.role.request.CreateRoleRequest;
import java.util.LinkedHashSet;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-14T09:08:15+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role toDto(CreateRoleRequest request) {
        if ( request == null ) {
            return null;
        }

        Role.RoleBuilder role = Role.builder();

        role.name( request.getName() );
        role.description( request.getDescription() );
        List<Long> list = request.getPermissionId();
        if ( list != null ) {
            role.permissionId( new LinkedHashSet<Long>( list ) );
        }

        return role.build();
    }

    @Override
    public void toUpdateRole(Role role, CreateRoleRequest request) {
        if ( request == null ) {
            return;
        }

        role.setName( request.getName() );
        role.setDescription( request.getDescription() );
        if ( role.getPermissionId() != null ) {
            List<Long> list = request.getPermissionId();
            if ( list != null ) {
                role.getPermissionId().clear();
                role.getPermissionId().addAll( list );
            }
            else {
                role.setPermissionId( null );
            }
        }
        else {
            List<Long> list = request.getPermissionId();
            if ( list != null ) {
                role.setPermissionId( new LinkedHashSet<Long>( list ) );
            }
        }
    }
}
