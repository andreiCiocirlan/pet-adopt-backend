package com.nimbletech.petadopt.user;

import com.nimbletech.petadopt.user.domain.UserRepository;
import org.springframework.modulith.ApplicationModuleInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserInitializer implements ApplicationModuleInitializer {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserInitializer(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initialize() {
        if (userRepository.count() == 0) {
            String password = passwordEncoder.encode("test123");
            userRepository.save(new User(null, "Alice Johnson", "alice@example.com", password, "4151254", Set.of(Role.ROLE_USER)));
            userRepository.save(new User(null, "Bob Smith", "bob@example.com", password, "4151254", Set.of(Role.ROLE_ADMIN)));
        }
    }
}
