package com.nimbletech.petadopt.pet.dto;

import com.nimbletech.petadopt.pet.model.AnimalType;
import jakarta.validation.constraints.NotBlank;

public record PetSearchRequest(
        @NotBlank
        AnimalType animalType,
        String breed,
        Integer age) {
}
