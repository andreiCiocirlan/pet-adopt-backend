package com.nimbletech.petadopt.pet.mapper;

import com.nimbletech.petadopt.pet.dto.CreatePetDto;
import com.nimbletech.petadopt.pet.dto.PetDto;
import com.nimbletech.petadopt.pet.model.Pet;

public class PetMapper {

    public static PetDto toDto(Pet pet) {
        if (pet == null) return null;
        return PetDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .age(pet.getAge())
                .type(pet.getType())
                .breed(pet.getBreed())
                .medicalHistory(pet.getMedicalHistory())
                .microchipId(pet.getMicrochipId())
                .status(pet.getStatus())
                .build();
    }

    public static Pet toEntity(CreatePetDto petDto) {
        if (petDto == null) return null;
        return new Pet(null,
                petDto.getName(),
                petDto.getAge(),
                petDto.getType(),
                petDto.getBreed(),
                petDto.getMedicalHistory(),
                petDto.getMicrochipId(),
                petDto.getStatus()
        );
    }

}
