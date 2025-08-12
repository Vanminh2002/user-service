package com.example.userservice.config;

import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.role.Role;
import com.example.userservice.role.RoleRepository;
import com.example.userservice.userRole.UserRole;
import com.example.userservice.userRole.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Configuration
public class ApplicationInnitConfig {

    @Bean
    ApplicationRunner applicationRunner(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            UserRoleRepository userRoleRepository

    ) {
        return args -> {
            if (userRepository.findByUsername("admin").isPresent()) {
                return;
            }
            Role adminRole = roleRepository.findByName("ADMIN");
            if (adminRole == null) {
                adminRole = Role.builder()
                        .name("ADMIN")
                        .description("Admin")
                        .build();
                adminRole = roleRepository.save(adminRole);
            }
            Set<Long> roleIds = new HashSet<>();
            roleIds.add(adminRole.getId());

            User initUser = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("manhden123@A"))
                    .roleId(roleIds)
                    .build();
            userRepository.save(initUser);


            UserRole userRole = UserRole.builder()
                    .roleId(adminRole.getId())
                    .userId(initUser.getId())
                    .build();
            userRoleRepository.save(userRole);
            log.info("Tài khoản đã được khởi tạo với username =: " + initUser.getUsername() + " và password =: " + "manhden123@A");
        };
    }
}
