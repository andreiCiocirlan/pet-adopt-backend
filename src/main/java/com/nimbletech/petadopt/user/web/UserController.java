package com.nimbletech.petadopt.user.web;

import com.nimbletech.petadopt.user.domain.models.CreateUserDto;
import com.nimbletech.petadopt.user.domain.models.UpdateUserDto;
import com.nimbletech.petadopt.user.domain.models.UserDto;
import com.nimbletech.petadopt.user.domain.models.UserUpdateRequest;
import com.nimbletech.petadopt.user.domain.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final GetUsersService getUsersService;
    private final GetUserByIdService getUserByIdService;
    private final CreateUserService createUserService;
    private final GetCurrentUserService getCurrentUserService;
    private final UpdateCurrentUserService updateCurrentUserService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return getUsersService.execute(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return getUserByIdService.execute(id);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid CreateUserDto dto) {
        return createUserService.execute(dto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        return getCurrentUserService.execute(email);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/me")
    public ResponseEntity<UserDto> updateCurrentUser(
            @RequestBody UpdateUserDto dto,
            Authentication authentication) {
        String email = authentication.getName();
        UserUpdateRequest request = new UserUpdateRequest(email, dto);
        return updateCurrentUserService.execute(request);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
