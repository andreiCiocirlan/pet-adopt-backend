package com.nimbletech.petadopt.user.controller;

import com.nimbletech.petadopt.user.dto.CreateUserDto;
import com.nimbletech.petadopt.user.dto.UpdateUserDto;
import com.nimbletech.petadopt.user.dto.UserDto;
import com.nimbletech.petadopt.user.dto.UserUpdateRequest;
import com.nimbletech.petadopt.user.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final GetUsersService getUsersService;
    private final GetUserByIdService getUserByIdService;
    private final CreateUserService createUserService;
    private final UpdateUserService updateUserService;
    private final DeleteUserService deleteUserService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return getUsersService.execute(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return getUserByIdService.execute(id);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto dto) {
        return createUserService.execute(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto dto) {
        UserUpdateRequest updateRequest = new UserUpdateRequest(id, dto);
        return updateUserService.execute(updateRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return deleteUserService.execute(id);
    }
}
