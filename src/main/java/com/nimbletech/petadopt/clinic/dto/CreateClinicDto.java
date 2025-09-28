package com.nimbletech.petadopt.clinic.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateClinicDto {
    private String name;
    private String address;
    private String phoneNumber;
    private double latitude;
    private double longitude;
}