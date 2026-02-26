package com.nimbletech.petadopt.clinic;

import com.nimbletech.petadopt.clinic.domain.ClinicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClinicApi {

    private final ClinicRepository clinicRepository;

    public Optional<Clinic> findById(String clinicId) {
        return clinicRepository.findById(clinicId);
    }

    public Optional<Clinic> findByName(String clinic) {
        return clinicRepository.findByName(clinic);
    }
}
