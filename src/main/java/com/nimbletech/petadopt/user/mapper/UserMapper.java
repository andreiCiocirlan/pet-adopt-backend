package com.nimbletech.petadopt.user.mapper;

import com.nimbletech.petadopt.user.dto.CreateUserDto;
import com.nimbletech.petadopt.user.dto.UserDto;
import com.nimbletech.petadopt.user.model.User;

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
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        return user;
    }
}
