package com.example.people_management.Configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.people_management.Entity.User;
import com.example.people_management.enums.Role;
import com.example.people_management.repository.UserRepository;

@Configuration
public class ApplicationInitConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByRole("HR").isEmpty()) {
                Set<String> roles = new HashSet<>();
                roles.add(Role.HR.name());
                User user = new User().builder()
                        .username("admin")
                        .password(
                                passwordEncoder.encode("admin123"))
                        .role(Role.HR.name())
                        .name("Administrator")
                        .age(30)
                        .build();
                userRepository.save(user);
            }
        };
    }
}