package com.nimbletech.petadopt.user;

import com.nimbletech.petadopt.user.model.User;
import com.nimbletech.petadopt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserApi {
    private final UserRepository userRepository;
    
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByGoogleId(String googleId) {
        return userRepository.findByGoogleId(googleId);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
