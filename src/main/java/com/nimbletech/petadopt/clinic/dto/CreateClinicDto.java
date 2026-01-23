package com.nimbletech.petadopt.clinic.dto;

import lombok.Builder;

@Builder
public record CreateClinicDto(
        String name,
        String address,
        String phoneNumber,
        double latitude,
        double longitude
) {
}