package com.nimbletech.petadopt.person.dto;

import com.nimbletech.petadopt.person.model.UserStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PersonDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private UserStatus status;
}
