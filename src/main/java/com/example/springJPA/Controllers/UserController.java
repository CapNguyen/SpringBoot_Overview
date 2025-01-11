package com.example.springJPA.Controllers;

import com.example.springJPA.DTOs.request.Request_UserCreate;
import com.example.springJPA.DTOs.request.Request_UserUpdate;
import com.example.springJPA.DTOs.response.ApiResponse;
import com.example.springJPA.DTOs.response.UserResponse;
import com.example.springJPA.Models.User;
import com.example.springJPA.Services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>();
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        apiResponse.setResult(userService.getAll());
        return apiResponse;
    }

    @GetMapping("{userId}")
    public ApiResponse<UserResponse> getUsers(@PathVariable("userId") String userId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> getMyInfo(){
        ApiResponse<UserResponse> apiResponse =new ApiResponse<>();
        apiResponse.setResult(userService.getMyInfo());
        return  apiResponse;
    }

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid Request_UserCreate request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.Create(request));
        return apiResponse;
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable("userId") String userId, @RequestBody Request_UserUpdate request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.Update(userId, request));
        return apiResponse;
    }

    @DeleteMapping("/{userId}")
    public ApiResponse deleteUser(@PathVariable("userId") String userId) {
        userService.Delete(userId);
        return ApiResponse.<String>builder()
                .result("User has been deleted")
                .build();
    }
}
