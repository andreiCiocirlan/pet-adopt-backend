package com.nimbletech.petadopt.user.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeleteUserService implements Command<Long, Void> {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<Void> execute(Long id) {
        log.info("Deleting user with id={}", id);
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
