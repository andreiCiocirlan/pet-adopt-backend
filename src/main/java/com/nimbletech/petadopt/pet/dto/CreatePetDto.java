package com.nimbletech.petadopt.pet.dto;

import com.nimbletech.petadopt.pet.model.AnimalType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CreatePetDto {
    private String name;
    private int age;
    private AnimalType type;
    private String breed;
    private String medicalHistory;
    private String microchipId;
    private List<String> imageUrls;
}
