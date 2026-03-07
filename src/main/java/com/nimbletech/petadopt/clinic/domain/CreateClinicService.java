package com.nimbletech.petadopt.clinic.domain;

import com.nimbletech.petadopt.clinic.Clinic;
import com.nimbletech.petadopt.common.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateClinicService implements Command<CreateClinicDto, ClinicDto> {

    private final ClinicRepository clinicRepository;

    @Override
    @Transactional
    public ResponseEntity<ClinicDto> execute(CreateClinicDto clinicDto) {
        log.info("Creating clinic for name={} and address={}", clinicDto.name(), clinicDto.address());
        Clinic clinic = ClinicMapper.toEntity(clinicDto);
        Clinic savedClinic = clinicRepository.save(clinic);
        return ResponseEntity.ok(ClinicMapper.toDto(savedClinic));
    }
}
