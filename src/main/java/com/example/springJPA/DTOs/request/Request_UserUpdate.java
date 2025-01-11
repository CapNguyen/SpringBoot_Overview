package com.example.springJPA.DTOs.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Request_UserUpdate {
    String password;
    String firstname;
    String lastname;
    LocalDate dob;
    List<String> roles;
}
