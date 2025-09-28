package com.nimbletech.petadopt.clinic.controller;

import com.nimbletech.petadopt.clinic.dto.ClinicDto;
import com.nimbletech.petadopt.clinic.dto.CreateClinicDto;
import com.nimbletech.petadopt.clinic.service.CreateClinicService;
import com.nimbletech.petadopt.clinic.service.GetAllClinicsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinics")
@RequiredArgsConstructor
public class ClinicController {

    private final CreateClinicService createClinicService;
    private final GetAllClinicsService getAllClinicsService;

    @PostMapping
    public ResponseEntity<ClinicDto> createClinic(@RequestBody CreateClinicDto clinicDto) {
        return createClinicService.execute(clinicDto);
    }

    @GetMapping
    public ResponseEntity<List<ClinicDto>> getAllClinics() {
        return getAllClinicsService.execute(null);
    }

}
