package com.example.springJPA.DTOs.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request_UserCreate {
    @NotBlank(message = "FIELD_CANNOT_EMPTY")
    @Size(min = 3, message = "USERNAME_INVALID")
    private String username;
    @NotBlank(message = "FIELD_CANNOT_EMPTY")
    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate dob;


}
