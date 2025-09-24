package com.nimbletech.petadopt.adoption.dto;

import com.nimbletech.petadopt.adoption.model.AdoptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class UpdateAdoptionRequestDto {
    private Long personId;
    private Long petId;
    private AdoptionStatus status;

}