package com.example.springJPA.Mappers;

import com.example.springJPA.DTOs.request.RoleRequest;
import com.example.springJPA.DTOs.response.RoleResponse;
import com.example.springJPA.Models.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
