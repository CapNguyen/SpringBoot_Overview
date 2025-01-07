package com.example.springJPA.Mappers;

import com.example.springJPA.DTOs.request.Request_UserCreate;
import com.example.springJPA.DTOs.request.Request_UserUpdate;
import com.example.springJPA.DTOs.response.UserResponse;
import com.example.springJPA.Models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mapping(source = "firstname", target = "lastname")
    User toUser(Request_UserCreate request);
//    @Mapping(target = "lastname", ignore=true)
    UserResponse toUserResponse(User user);

    List<UserResponse> toListUserResponse(List<User> users);

    void updateUser(@MappingTarget User user, Request_UserUpdate request);
}
