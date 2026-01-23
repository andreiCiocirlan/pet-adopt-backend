package com.nimbletech.petadopt.pet.dto;

import com.nimbletech.petadopt.pet.model.AnimalType;
import lombok.Builder;
import lombok.Data;

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
