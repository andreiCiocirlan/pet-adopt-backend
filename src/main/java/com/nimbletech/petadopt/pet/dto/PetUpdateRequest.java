package com.nimbletech.petadopt.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PetUpdateRequest {
    private Long id;
    private UpdatePetDto updatePetDto;
}