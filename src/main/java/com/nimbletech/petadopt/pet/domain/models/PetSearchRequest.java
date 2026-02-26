package com.nimbletech.petadopt.pet.domain.models;

import com.nimbletech.petadopt.pet.domain.AnimalType;
import com.nimbletech.petadopt.pet.domain.PetStatus;
import org.springframework.data.domain.Pageable;

public record PetSearchRequest(
        AnimalType animalType,
        String breed,
        Integer age,
        PetStatus status,
        String clinicId,
        Pageable pageable) {
}
