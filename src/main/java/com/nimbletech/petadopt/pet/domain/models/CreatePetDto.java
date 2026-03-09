package com.nimbletech.petadopt.pet.domain.models;

import com.nimbletech.petadopt.pet.domain.AnimalType;
import com.nimbletech.petadopt.pet.domain.Breed;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CreatePetDto (
    @NotNull String name,
    @Min(0) int age,
    @NotNull AnimalType type,
    @NotNull Breed breed,
    String health,
    String characteristics,
    boolean isNeutered,
    boolean hasMicrochip,
    boolean isVaccinated,
    String clinicId,
    Set<String> imageUrls
){
    @AssertTrue(message = "Invalid breed for animal type")
    boolean isValidBreed() {
        return breed.isValidFor(type);
    }
}
