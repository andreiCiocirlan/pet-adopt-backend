package com.nimbletech.petadopt.user.domain;

import com.nimbletech.petadopt.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User findByGoogleId(String googleId);
}