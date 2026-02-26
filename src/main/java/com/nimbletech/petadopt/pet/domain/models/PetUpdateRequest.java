package com.nimbletech.petadopt.pet.domain.models;

public record PetUpdateRequest(
        String id,
        UpdatePetDto updatePetDto
) {
}