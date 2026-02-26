package com.nimbletech.petadopt.user;

import java.util.Optional;

public interface UserApi {
    Optional<User> findById(Long userId);

    Optional<User> findByEmail(String email);

    User findByGoogleId(String googleId);

    User save(User user);

    long count();
}
