package com.nimbletech.petadopt.pet.mapper;

import com.nimbletech.petadopt.clinic.mapper.ClinicMapper;
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
