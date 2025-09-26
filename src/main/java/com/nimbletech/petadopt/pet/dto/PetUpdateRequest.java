package com.nimbletech.petadopt.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PetUpdateRequest {
    private String id;
    private UpdatePetDto updatePetDto;
}