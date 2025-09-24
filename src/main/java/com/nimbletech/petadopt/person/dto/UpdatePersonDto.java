package com.nimbletech.petadopt.person.dto;

import com.nimbletech.petadopt.person.model.PersonStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdatePersonDto {

    private String name;
    private String email;
    private String phone;
    private String address;
    private PersonStatus status;
}
