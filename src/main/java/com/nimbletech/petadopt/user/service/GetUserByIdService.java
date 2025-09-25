package com.nimbletech.petadopt.user.service;

import com.nimbletech.petadopt.Query;
import com.nimbletech.petadopt.user.dto.UserDto;
import com.nimbletech.petadopt.user.mapper.UserMapper;
import com.nimbletech.petadopt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetUserByIdService implements Query<Long, UserDto> {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<UserDto> execute(Long id) {
        log.info("Executing {}", getClass().getSimpleName());
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
