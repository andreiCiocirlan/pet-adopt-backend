package com.nimbletech.petadopt.user.domain;

import com.nimbletech.petadopt.common.Query;
import com.nimbletech.petadopt.user.User;
import com.nimbletech.petadopt.user.domain.models.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetUsersService implements Query<Void, List<UserDto>> {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<List<UserDto>> execute(Void input) {
        log.info("Getting all users");
        List<User> users = userRepository.findAll();
        List<UserDto> dtoList = users.stream()
                                        .map(UserMapper::toDto)
                                        .toList();
        return ResponseEntity.ok(dtoList);
    }
}
