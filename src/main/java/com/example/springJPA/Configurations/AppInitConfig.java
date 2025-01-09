package com.example.springJPA.Configurations;

import com.example.springJPA.Enums.Role;
import com.example.springJPA.Models.User;
import com.example.springJPA.Repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppInitConfig {
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            var roles = new HashSet<String>();
            roles.add(Role.ADMIN.name());
            if (userRepository.findByUsername("admin").isEmpty()) {
                User u = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
//                        .roles(roles)
                        .build();
                userRepository.save(u);
                log.warn("admin user has been created with default password: admin, please change it");
            }
        };
    }
}
