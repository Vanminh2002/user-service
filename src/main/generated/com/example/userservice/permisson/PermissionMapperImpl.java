package com.example.userservice.permisson;

import com.example.userservice.permisson.permission.request.CreatePermissionRequest;
import com.example.userservice.permisson.permission.response.PermissionResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-14T09:08:14+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class PermissionMapperImpl implements PermissionMapper {

    @Override
    public Permission toEntity(CreatePermissionRequest request) {
        if ( request == null ) {
            return null;
        }

        Permission permission = new Permission();

        permission.setName( request.getName() );
        permission.setDescription( request.getDescription() );

        return permission;
    }

    @Override
    public PermissionResponse toResponse(Permission permission) {
        if ( permission == null ) {
            return null;
        }

        PermissionResponse.PermissionResponseBuilder permissionResponse = PermissionResponse.builder();

        permissionResponse.id( permission.getId() );
        permissionResponse.name( permission.getName() );
        permissionResponse.description( permission.getDescription() );

        return permissionResponse.build();
    }

    @Override
    public void toUpdatePermission(Permission permission, CreatePermissionRequest request) {
        if ( request == null ) {
            return;
        }

        permission.setName( request.getName() );
        permission.setDescription( request.getDescription() );
    }
}
