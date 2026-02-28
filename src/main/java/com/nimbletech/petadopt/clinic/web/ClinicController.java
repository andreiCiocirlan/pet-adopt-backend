package com.nimbletech.petadopt.clinic.web;

import com.nimbletech.petadopt.clinic.domain.ClinicDto;
import com.nimbletech.petadopt.clinic.domain.CreateClinicDto;
import com.nimbletech.petadopt.clinic.domain.CreateClinicService;
import com.nimbletech.petadopt.clinic.domain.GetAllClinicsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinics")
@RequiredArgsConstructor
public class ClinicController {

    private final CreateClinicService createClinicService;
    private final GetAllClinicsService getAllClinicsService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ClinicDto> createClinic(@RequestBody CreateClinicDto clinicDto) {
        return createClinicService.execute(clinicDto);
    }

    @GetMapping
    public ResponseEntity<List<ClinicDto>> getAllClinics() {
        return getAllClinicsService.execute(null);
    }

}
