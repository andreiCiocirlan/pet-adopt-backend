package com.nimbletech.petadopt.pet.dto;

import com.nimbletech.petadopt.pet.model.AnimalType;
import lombok.Builder;

import java.util.Set;

@Builder
public record UpdatePetDto(String name, int age, AnimalType type, String breed, String health, String characteristics,
                           Set<String> imageUrls) {
}
