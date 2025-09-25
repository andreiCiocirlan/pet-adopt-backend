package com.nimbletech.petadopt.adoption.dto;

import com.nimbletech.petadopt.adoption.model.AdoptionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdoptionStatusUpdateDto {
    private AdoptionStatus status;
}
