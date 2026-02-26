package com.nimbletech.petadopt.user.domain;

import com.nimbletech.petadopt.common.Command;
import com.nimbletech.petadopt.user.User;
import com.nimbletech.petadopt.user.domain.models.CreateUserDto;
import com.nimbletech.petadopt.user.domain.models.UserDto;
import com.nimbletech.petadopt.user.web.EmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateUserService implements Command<CreateUserDto, UserDto> {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<UserDto> execute(CreateUserDto dto) {
        log.info("Creating user with email={}", dto.email());
        User user = UserMapper.toEntity(dto);
        userRepository.findByEmail(dto.email()).ifPresent(u -> {
            throw new EmailAlreadyExistsException("Email is already in use");
        });

        String hashedPassword = passwordEncoder.encode(dto.password());
        user.setPassword(hashedPassword);
        user.setRoles(Set.of(Role.ROLE_USER));

        User saved = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(UserMapper.toDto(saved));
    }
}
