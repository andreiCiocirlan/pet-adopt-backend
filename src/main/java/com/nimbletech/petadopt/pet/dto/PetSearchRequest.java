package com.nimbletech.petadopt.pet.dto;

import com.nimbletech.petadopt.pet.model.AnimalType;
import com.nimbletech.petadopt.pet.model.PetStatus;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Pageable;

public record PetSearchRequest(
        @NotBlank
        AnimalType animalType,
        String breed,
        Integer age,
        PetStatus status,
        String clinicId,
        Pageable pageable) {
}
