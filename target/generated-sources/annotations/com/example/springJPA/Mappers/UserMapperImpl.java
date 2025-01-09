package com.example.springJPA.Mappers;

import com.example.springJPA.DTOs.request.Request_UserCreate;
import com.example.springJPA.DTOs.request.Request_UserUpdate;
import com.example.springJPA.DTOs.response.UserResponse;
import com.example.springJPA.Models.Role;
import com.example.springJPA.Models.User;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(Request_UserCreate request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( request.getUsername() );
        user.password( request.getPassword() );
        user.firstname( request.getFirstname() );
        user.lastname( request.getLastname() );
        user.dob( request.getDob() );

        return user.build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.username( user.getUsername() );
        userResponse.password( user.getPassword() );
        userResponse.firstname( user.getFirstname() );
        userResponse.lastname( user.getLastname() );
        userResponse.dob( user.getDob() );
        List<Role> list = user.getRoles();
        if ( list != null ) {
            userResponse.roles( new LinkedHashSet<Role>( list ) );
        }

        return userResponse.build();
    }

    @Override
    public List<UserResponse> toListUserResponse(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( users.size() );
        for ( User user : users ) {
            list.add( toUserResponse( user ) );
        }

        return list;
    }

    @Override
    public void updateUser(User user, Request_UserUpdate request) {
        if ( request == null ) {
            return;
        }

        user.setPassword( request.getPassword() );
        user.setFirstname( request.getFirstname() );
        user.setLastname( request.getLastname() );
        user.setDob( request.getDob() );
    }
}
