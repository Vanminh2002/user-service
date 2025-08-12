package com.example.userservice.mapper;

import com.example.userservice.dto.user.request.UpdateUserRequest;
import com.example.userservice.dto.user.request.UserCreateRequest;
import com.example.userservice.dto.user.response.UserResponse;
import com.example.userservice.entity.User;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-07T13:17:31+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toDto(UserCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.fullName( request.getFullName() );
        user.firstName( request.getFirstName() );
        user.email( request.getEmail() );
        user.username( request.getUsername() );
        user.password( request.getPassword() );
        user.image( request.getImage() );
        user.address( request.getAddress() );
        user.phone( request.getPhone() );
        List<Long> list = request.getRoleId();
        if ( list != null ) {
            user.roleId( new LinkedHashSet<Long>( list ) );
        }

        return user.build();
    }

    @Override
    public UserResponse toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setFullName( user.getFullName() );
        userResponse.setFirstName( user.getFirstName() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setPassword( user.getPassword() );
        userResponse.setImage( user.getImage() );
        userResponse.setAddress( user.getAddress() );
        userResponse.setPhone( user.getPhone() );
        userResponse.setRoleId( longSetToStringList( user.getRoleId() ) );

        return userResponse;
    }

    @Override
    public void toUpdateUser(User user, UpdateUserRequest request) {
        if ( request == null ) {
            return;
        }

        user.setUsername( request.getUsername() );
        user.setPassword( request.getPassword() );
    }

    protected List<String> longSetToStringList(Set<Long> set) {
        if ( set == null ) {
            return null;
        }

        List<String> list = new ArrayList<String>( set.size() );
        for ( Long long1 : set ) {
            list.add( String.valueOf( long1 ) );
        }

        return list;
    }
}
