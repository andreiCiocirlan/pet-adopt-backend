package com.nimbletech.petadopt.pet.repository;

import com.nimbletech.petadopt.pet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, String> {
}