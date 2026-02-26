package com.nimbletech.petadopt.pet.domain.models;

import com.nimbletech.petadopt.clinic.domain.ClinicDto;
import com.nimbletech.petadopt.pet.domain.AnimalType;
import com.nimbletech.petadopt.pet.domain.PetStatus;
import lombok.Builder;

import java.util.Set;

@Builder
public record PetDto(
        String id,
        String name,
        int age,
        AnimalType type,
        String breed,
        String health,
        String characteristics,
        PetStatus status,
        ClinicDto clinic,
        Set<String> imageUrls
) {
}
