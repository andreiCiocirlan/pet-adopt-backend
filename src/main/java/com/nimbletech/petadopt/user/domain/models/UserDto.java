package com.nimbletech.petadopt.user.domain.models;

import lombok.Builder;

@Builder
public record UserDto(
        Long id,
        String name,
        String email,
        String phone,
        String address
) {
}
