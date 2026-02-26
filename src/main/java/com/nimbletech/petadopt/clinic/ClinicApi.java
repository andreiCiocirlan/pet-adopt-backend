package com.nimbletech.petadopt.clinic;

import java.util.Optional;

public interface ClinicApi {
    Optional<Clinic> findById(String clinicId);

    Optional<Clinic> findByName(String clinic);

    long count();

    Clinic save(Clinic clinic);
}
