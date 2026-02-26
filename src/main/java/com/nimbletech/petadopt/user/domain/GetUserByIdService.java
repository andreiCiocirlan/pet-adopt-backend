package com.nimbletech.petadopt.user.domain;

import com.nimbletech.petadopt.common.Query;
import com.nimbletech.petadopt.user.domain.models.UserDto;
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
        log.info("Getting user by id={}", id);
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
