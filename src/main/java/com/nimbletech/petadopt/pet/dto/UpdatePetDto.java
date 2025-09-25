package com.nimbletech.petadopt.pet.dto;

import com.nimbletech.petadopt.pet.model.AnimalType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdatePetDto {
    private String name;
    private int age;
    private AnimalType type;
    private String breed;
    private String medicalHistory;
    private String microchipId;
    private String imageUrl;
}
