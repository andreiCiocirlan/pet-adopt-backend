package com.nimbletech.petadopt.clinic.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.clinic.dto.ClinicDto;
import com.nimbletech.petadopt.clinic.dto.CreateClinicDto;
import com.nimbletech.petadopt.clinic.mapper.ClinicMapper;
import com.nimbletech.petadopt.clinic.model.Clinic;
import com.nimbletech.petadopt.clinic.repository.ClinicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateClinicService implements Command<CreateClinicDto, ClinicDto> {

    private final ClinicRepository clinicRepository;

    @Override
    public ResponseEntity<ClinicDto> execute(CreateClinicDto clinicDto) {
        log.info("Creating clinic for name={} and address={}", clinicDto.name(), clinicDto.address());
        Clinic clinic = ClinicMapper.toEntity(clinicDto);
        Clinic savedClinic = clinicRepository.save(clinic);
        return ResponseEntity.ok(ClinicMapper.toDto(savedClinic));
    }
}
