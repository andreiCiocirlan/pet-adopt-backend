package com.nimbletech.petadopt.pet.domain.models;

public record SetClinicForPetRequest(
        String petId,
        String clinicId
) {
}
