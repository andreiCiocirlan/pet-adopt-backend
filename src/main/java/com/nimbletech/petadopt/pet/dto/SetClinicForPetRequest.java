package com.nimbletech.petadopt.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SetClinicForPetRequest {
    String petId;
    String clinicId;
}
