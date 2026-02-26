package com.nimbletech.petadopt.user.domain.models;

public record UserUpdateRequest(
        String email,
        UpdateUserDto updateUserDto
) {
}