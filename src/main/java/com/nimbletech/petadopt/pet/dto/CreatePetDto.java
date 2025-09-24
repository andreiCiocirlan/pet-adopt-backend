package com.nimbletech.petadopt.pet.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreatePetDto {
    private String name;
    private int age;
    private String type;
    private String breed;
    private String medicalHistory;
    private String microchipId;
    private String status; // e.g. "available", "adopted"
}
