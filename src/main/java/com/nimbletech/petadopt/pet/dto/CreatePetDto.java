package com.nimbletech.petadopt.pet.dto;

import com.nimbletech.petadopt.clinic.dto.ClinicDto;
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
    private String health;
    private String characteristics;
    private ClinicDto clinic;
    private List<String> imageUrls;
}
