package com.nimbletech.petadopt.pet.domain.models;

import com.nimbletech.petadopt.pet.domain.AnimalType;
import com.nimbletech.petadopt.pet.domain.Breed;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;

@Builder
public record UpdatePetDto(
        @NotNull String name,
        @Min(0) int age,
        @NotNull AnimalType type,
        @NotNull Breed breed,
        String characteristics,
        boolean isNeutered,
        boolean hasMicrochip,
        boolean isVaccinated,
        Set<String> imageUrls
) {
    @AssertTrue(message = "Invalid breed for animal type")
    boolean isValidBreed() {
        return breed.isValidFor(type);
    }
}
