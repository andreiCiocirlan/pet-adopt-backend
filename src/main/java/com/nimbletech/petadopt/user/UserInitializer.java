package com.nimbletech.petadopt.user;

import org.springframework.modulith.ApplicationModuleInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserInitializer implements ApplicationModuleInitializer {
    private final UserApi userApi;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserInitializer(UserApi userApi, BCryptPasswordEncoder passwordEncoder) {
        this.userApi = userApi;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initialize() {
        if (userApi.count() == 0) {
            String password = passwordEncoder.encode("test123");
            userApi.save(new User(null, "Alice Johnson", "alice@example.com", password, "4151254", Set.of(Role.ROLE_USER)));
            userApi.save(new User(null, "Bob Smith", "bob@example.com", password, "4151254", Set.of(Role.ROLE_ADMIN)));
        }
    }
}
