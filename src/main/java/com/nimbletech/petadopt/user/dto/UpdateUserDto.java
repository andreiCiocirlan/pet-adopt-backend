package com.nimbletech.petadopt.user.dto;

import com.nimbletech.petadopt.user.model.UserStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateUserDto {

    private String name;
    private String email;
    private String phone;
    private String address;
    private UserStatus status;
}
