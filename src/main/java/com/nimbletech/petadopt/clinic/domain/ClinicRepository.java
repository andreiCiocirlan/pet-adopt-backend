package com.nimbletech.petadopt.clinic.domain;

import com.nimbletech.petadopt.clinic.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ClinicRepository extends JpaRepository<Clinic, String> {
    Optional<Clinic> findByName(String clinic);
}
