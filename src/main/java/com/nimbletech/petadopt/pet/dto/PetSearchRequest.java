package com.nimbletech.petadopt.pet.dto;

import com.nimbletech.petadopt.pet.model.AnimalType;
import com.nimbletech.petadopt.pet.model.PetStatus;
import org.springframework.data.domain.Pageable;

public record PetSearchRequest(
        AnimalType animalType,
        String breed,
        Integer age,
        PetStatus status,
        String clinicId,
        Pageable pageable) {
}
