package com.nimbletech.petadopt.pet.dto;

public record PetUpdateRequest(
        String id,
        UpdatePetDto updatePetDto
) {
}