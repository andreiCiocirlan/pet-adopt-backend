package com.nimbletech.petadopt.clinic.domain;

import com.nimbletech.petadopt.clinic.Clinic;
import com.nimbletech.petadopt.clinic.ClinicApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
class ClinicApiService implements ClinicApi {

    private final ClinicRepository clinicRepository;

    @Override
    public Optional<Clinic> findById(String clinicId) {
        return clinicRepository.findById(clinicId);
    }

    @Override
    public Optional<Clinic> findByName(String clinic) {
        return clinicRepository.findByName(clinic);
    }

    @Override
    public long count() {
        return clinicRepository.count();
    }

    @Override
    public Clinic save(Clinic clinic) {
        return clinicRepository.save(clinic);
    }
}
