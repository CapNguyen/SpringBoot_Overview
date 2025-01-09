package com.example.springJPA.DTOs.response;

import com.example.springJPA.Models.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String password;
    String firstname;
    String lastname;
    LocalDate dob;
    Set<Role> roles;
}
