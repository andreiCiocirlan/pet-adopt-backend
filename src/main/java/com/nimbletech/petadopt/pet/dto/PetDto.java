package com.nimbletech.petadopt.pet.dto;

import com.nimbletech.petadopt.pet.model.AnimalType;
import com.nimbletech.petadopt.pet.model.PetStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PetDto {
    private String id;
    private String name;
    private int age;
    private AnimalType type;
    private String breed;
    private String medicalHistory;
    private String microchipId;
    private PetStatus status;
    private List<String> imageUrls;

}
