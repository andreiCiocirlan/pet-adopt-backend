package com.nimbletech.petadopt.adoption.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AdoptionRequestUpdateRequest {

    private Long id;
    private UpdateAdoptionRequestDto dto;

}