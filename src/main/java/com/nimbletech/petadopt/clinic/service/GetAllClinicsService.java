package com.nimbletech.petadopt.clinic.service;

import com.nimbletech.petadopt.common.Query;
import com.nimbletech.petadopt.clinic.dto.ClinicDto;
import com.nimbletech.petadopt.clinic.mapper.ClinicMapper;
import com.nimbletech.petadopt.clinic.model.Clinic;
import com.nimbletech.petadopt.clinic.repository.ClinicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetAllClinicsService implements Query<Void, List<ClinicDto>> {

    private final ClinicRepository clinicRepository;

    @Override
    public ResponseEntity<List<ClinicDto>> execute(Void input) {
        log.info("Getting all clinics");
        List<Clinic> clinics = clinicRepository.findAll();
        List<ClinicDto> dtoList = clinics.stream()
                .map(ClinicMapper::toDto)
                .toList();
        return ResponseEntity.ok(dtoList);
    }
}
