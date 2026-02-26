package com.nimbletech.petadopt.user.domain;

import com.nimbletech.petadopt.user.User;
import com.nimbletech.petadopt.user.domain.models.CreateUserDto;
import com.nimbletech.petadopt.user.domain.models.UserDto;

public class UserMapper {

    public static UserDto toDto(User user) {
        if (user == null) return null;
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
    }

    public static User toEntity(CreateUserDto dto) {
        if (dto == null) return null;
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPhone(dto.phone());
        user.setAddress(dto.address());
        return user;
    }
}
