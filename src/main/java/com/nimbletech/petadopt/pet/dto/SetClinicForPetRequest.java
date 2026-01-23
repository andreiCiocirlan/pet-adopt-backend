package com.nimbletech.petadopt.pet.dto;

public record SetClinicForPetRequest(
        String petId,
        String clinicId
) {
}
