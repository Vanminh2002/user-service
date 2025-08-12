package com.example.userservice.permisson;

import com.example.userservice.permisson.permission.request.CreatePermissionRequest;
import com.example.userservice.permisson.permission.response.PermissionResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-07T13:17:31+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
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

        PermissionResponse permissionResponse = new PermissionResponse();

        permissionResponse.setId( permission.getId() );
        permissionResponse.setName( permission.getName() );
        permissionResponse.setDescription( permission.getDescription() );

        return permissionResponse;
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
