package com.nimbletech.petadopt.clinic;

import com.nimbletech.petadopt.clinic.model.Clinic;
import com.nimbletech.petadopt.clinic.repository.ClinicRepository;
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
}
