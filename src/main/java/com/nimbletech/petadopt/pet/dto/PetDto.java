package com.nimbletech.petadopt.pet.dto;

import com.nimbletech.petadopt.pet.model.PetStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PetDto {
    private Long id;
    private String name;
    private int age;
    private String type;
    private String breed;
    private String medicalHistory;
    private String microchipId;
    private PetStatus status;

}
