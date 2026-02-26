package com.nimbletech.petadopt.user.domain;

import com.nimbletech.petadopt.common.Command;
import com.nimbletech.petadopt.user.User;
import com.nimbletech.petadopt.user.domain.models.UpdateUserDto;
import com.nimbletech.petadopt.user.domain.models.UserDto;
import com.nimbletech.petadopt.user.domain.models.UserUpdateRequest;
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
        String email = request.email();
        UpdateUserDto dto = request.updateUserDto();
        log.info("Updating user with email={}", email);

        return userRepository.findByEmail(email)
                .map(existing -> {
                    existing.setName(dto.name());
                    existing.setPhone(dto.phone());
                    existing.setAddress(dto.address());
                    User updated = userRepository.save(existing);
                    return ResponseEntity.ok(UserMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}