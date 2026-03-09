package com.nimbletech.petadopt.pet.domain.models;

import com.nimbletech.petadopt.pet.domain.AnimalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CreatePetDto (
    @NotNull String name,
    @Min(0) int age,
    @NotNull AnimalType type,
    @NotBlank String breed,
    String health,
    String characteristics,
    boolean isNeutered,
    boolean hasMicrochip,
    boolean isVaccinated,
    String clinicId,
    Set<String> imageUrls
){}
