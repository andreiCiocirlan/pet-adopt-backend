package com.nimbletech.petadopt.user.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.user.dto.CreateUserDto;
import com.nimbletech.petadopt.user.dto.UserDto;
import com.nimbletech.petadopt.user.mapper.UserMapper;
import com.nimbletech.petadopt.user.model.User;
import com.nimbletech.petadopt.user.model.UserStatus;
import com.nimbletech.petadopt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateUserService implements Command<CreateUserDto, UserDto> {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<UserDto> execute(CreateUserDto dto) {
        log.info("Executing {}", getClass().getSimpleName());
        User user = UserMapper.toEntity(dto);
        user.setStatus(UserStatus.APPLICANT);
        User saved = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(UserMapper.toDto(saved));
    }
}
