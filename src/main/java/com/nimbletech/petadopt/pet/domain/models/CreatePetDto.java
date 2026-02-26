package com.nimbletech.petadopt.pet.domain.models;

import com.nimbletech.petadopt.pet.domain.AnimalType;
import lombok.Builder;

import java.util.Set;

@Builder
public record CreatePetDto (
    String name,
    int age,
    AnimalType type,
    String breed,
    String health,
    String characteristics,
    String clinicId,
    Set<String> imageUrls
){}
