package com.nimbletech.petadopt.user.dto;

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
