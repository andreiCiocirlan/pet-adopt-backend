package com.nimbletech.petadopt.clinic.domain;

import com.nimbletech.petadopt.clinic.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, String> {
}
