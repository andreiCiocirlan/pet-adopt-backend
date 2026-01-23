package com.nimbletech.petadopt.pet.dto;

import com.nimbletech.petadopt.clinic.dto.ClinicDto;
import com.nimbletech.petadopt.pet.model.AnimalType;
import com.nimbletech.petadopt.pet.model.PetStatus;
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
