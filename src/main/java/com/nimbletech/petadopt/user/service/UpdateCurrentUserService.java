package com.nimbletech.petadopt.user.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.user.dto.UpdateUserDto;
import com.nimbletech.petadopt.user.dto.UserDto;
import com.nimbletech.petadopt.user.dto.UserUpdateRequest;
import com.nimbletech.petadopt.user.mapper.UserMapper;
import com.nimbletech.petadopt.user.model.User;
import com.nimbletech.petadopt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateCurrentUserService implements Command<UserUpdateRequest, UserDto> {

    private final UserRepository userRepository;

    public ResponseEntity<UserDto> execute(UserUpdateRequest request) {
        String email = request.getEmail();
        UpdateUserDto dto = request.getUpdateUserDto();
        log.info("Updating user with email={}", email);

        return userRepository.findByEmail(email)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setPhone(dto.getPhone());
                    existing.setAddress(dto.getAddress());
                    User updated = userRepository.save(existing);
                    return ResponseEntity.ok(UserMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}