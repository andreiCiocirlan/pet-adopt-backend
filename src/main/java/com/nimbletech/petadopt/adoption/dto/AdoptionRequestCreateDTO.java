package com.nimbletech.petadopt.adoption.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class AdoptionRequestCreateDTO {

    private Long personId;
    private Long petId;
}
