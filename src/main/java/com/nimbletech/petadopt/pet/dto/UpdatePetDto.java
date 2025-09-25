package com.nimbletech.petadopt.pet.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdatePetDto {
    private String name;
    private int age;
    private String type;
    private String breed;
    private String medicalHistory;
    private String microchipId;
}
