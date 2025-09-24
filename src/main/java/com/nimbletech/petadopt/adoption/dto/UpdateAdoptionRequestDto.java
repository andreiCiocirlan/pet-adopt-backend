package com.nimbletech.petadopt.adoption.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
public class UpdateAdoptionRequestDto {
    private Long personId;
    private Long petId;
    private String status;

}