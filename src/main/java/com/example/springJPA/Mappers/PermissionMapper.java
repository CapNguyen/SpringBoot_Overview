package com.example.springJPA.Mappers;

import com.example.springJPA.DTOs.request.PermissionRequest;
import com.example.springJPA.DTOs.response.PermissionResponse;
import com.example.springJPA.Models.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);
    PermissionResponse toPermissionResponse(Permission permission);
}
