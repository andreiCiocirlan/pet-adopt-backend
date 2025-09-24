package com.nimbletech.petadopt.repository;

import com.nimbletech.petadopt.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}