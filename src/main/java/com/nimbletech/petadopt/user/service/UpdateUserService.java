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
@RequiredArgsConstructor
@Service
public class UpdateUserService implements Command<UserUpdateRequest, UserDto> {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<UserDto> execute(UserUpdateRequest updateRequest) {
        Long id = updateRequest.getId();
        UpdateUserDto dto = updateRequest.getUpdateUserDto();
        log.info("Updating user with id={}", id);

        return userRepository.findById(id)
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
