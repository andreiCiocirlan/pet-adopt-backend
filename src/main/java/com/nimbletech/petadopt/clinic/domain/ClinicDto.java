package com.nimbletech.petadopt.clinic.domain;

import lombok.Builder;
import org.springframework.modulith.NamedInterface;

@NamedInterface
@Builder
public record ClinicDto(String id, String name, String address, String phoneNumber, double latitude, double longitude) {

}
