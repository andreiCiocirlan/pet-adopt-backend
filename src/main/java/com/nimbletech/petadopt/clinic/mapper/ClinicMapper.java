package com.nimbletech.petadopt.clinic.mapper;

import com.nimbletech.petadopt.clinic.dto.ClinicDto;
import com.nimbletech.petadopt.clinic.dto.CreateClinicDto;
import com.nimbletech.petadopt.clinic.model.Clinic;

public class ClinicMapper {

    public static ClinicDto toDto(Clinic clinic) {
        if (clinic == null) {
            return null;
        }
        return ClinicDto.builder()
                .id(clinic.getId())
                .name(clinic.getName())
                .address(clinic.getAddress())
                .phoneNumber(clinic.getPhoneNumber())
                .latitude(clinic.getLatitude())
                .longitude(clinic.getLongitude())
                .build();
    }

    public static Clinic toEntity(CreateClinicDto dto) {
        if (dto == null) {
            return null;
        }
        Clinic clinic = new Clinic();
        clinic.setName(dto.name());
        clinic.setAddress(dto.address());
        clinic.setPhoneNumber(dto.phoneNumber());
        clinic.setLatitude(dto.latitude());
        clinic.setLongitude(dto.longitude());
        return clinic;
    }

}
