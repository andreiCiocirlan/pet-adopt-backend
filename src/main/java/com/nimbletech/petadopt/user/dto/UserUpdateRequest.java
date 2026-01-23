package com.nimbletech.petadopt.user.dto;

public record UserUpdateRequest(
        String email,
        UpdateUserDto updateUserDto
) {
}