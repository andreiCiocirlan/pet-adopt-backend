package com.nimbletech.petadopt.user.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateUserDto {

    private String name;
    private String email;
    private String phone;
    private String address;
}
