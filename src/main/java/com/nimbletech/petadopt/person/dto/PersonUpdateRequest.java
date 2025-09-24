package com.nimbletech.petadopt.person.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PersonUpdateRequest {

    private Long id;
    private UpdatePersonDto updatePersonDto;

}