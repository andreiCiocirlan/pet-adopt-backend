package com.nimbletech.petadopt.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserUpdateRequest {

    private String email;
    private UpdateUserDto updateUserDto;

}