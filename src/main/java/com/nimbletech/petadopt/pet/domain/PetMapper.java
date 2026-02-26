package com.nimbletech.petadopt.pet.domain;

import com.nimbletech.petadopt.clinic.domain.ClinicMapper;
import com.nimbletech.petadopt.pet.Pet;
import com.nimbletech.petadopt.pet.domain.models.CreatePetDto;
import com.nimbletech.petadopt.pet.domain.models.PetDto;

public class PetMapper {

    public static PetDto toDto(Pet pet) {
        if (pet == null) return null;
        return PetDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .age(pet.getAge())
                .type(pet.getType())
                .breed(pet.getBreed())
                .health(pet.getHealth())
                .characteristics(pet.getCharacteristics())
                .imageUrls(pet.getImageUrls())
                .status(pet.getStatus())
                .clinic(ClinicMapper.toDto(pet.getClinic()))
                .build();
    }

    public static Pet toEntity(CreatePetDto petDto) {
        if (petDto == null) return null;
        Pet pet = new Pet();
        pet.setName(petDto.name());
        pet.setAge(petDto.age());
        pet.setType(petDto.type());
        pet.setBreed(petDto.breed());
        pet.setHealth(petDto.health());
        pet.setCharacteristics(petDto.characteristics());
        pet.setImageUrls(petDto.imageUrls());
        return pet;
    }

}
