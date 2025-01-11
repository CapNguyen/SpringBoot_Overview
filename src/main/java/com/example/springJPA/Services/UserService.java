package com.example.springJPA.Services;

import com.example.springJPA.DTOs.request.Request_UserCreate;
import com.example.springJPA.DTOs.request.Request_UserUpdate;
import com.example.springJPA.DTOs.response.UserResponse;
import com.example.springJPA.Enums.Role;
import com.example.springJPA.Exceptions.ApplicationRuntimeException;
import com.example.springJPA.Exceptions.ErrorCode;
import com.example.springJPA.Mappers.UserMapper;
import com.example.springJPA.Models.User;
import com.example.springJPA.Repositories.RoleRepository;
import com.example.springJPA.Repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//Last 2 annotations do dependency injection ( replace for @Autowired)
public class UserService {
    RoleRepository roleRepository;
    UserRepository userRepository;
    UserMapper userMapper;

    @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasAuthority('VIEW_DATA')")
    public List<UserResponse> getAll() {
        return userMapper.toListUserResponse(userRepository.findAll());
    }

//    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getById(String userId) {
        return userMapper.toUserResponse(userRepository.findById(userId).orElseThrow(() -> new ApplicationRuntimeException(ErrorCode.USER_NOT_EXISTED)));
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        var currentUsername = context.getAuthentication().getName();
        User u = userRepository.findByUsername(currentUsername).orElseThrow(() -> new ApplicationRuntimeException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(u);
    }

    public UserResponse Create(Request_UserCreate request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ApplicationRuntimeException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
//        user.setRoles(roles);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse Update(String userId, Request_UserUpdate request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationRuntimeException(ErrorCode.USER_NOT_EXISTED));
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles= roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public void Delete(String userId) {
        userRepository.deleteById(userId);
    }
}
