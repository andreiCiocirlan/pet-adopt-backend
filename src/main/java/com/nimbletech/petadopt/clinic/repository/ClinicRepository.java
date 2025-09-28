package com.nimbletech.petadopt.clinic.repository;

import com.nimbletech.petadopt.clinic.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, String> {
}
