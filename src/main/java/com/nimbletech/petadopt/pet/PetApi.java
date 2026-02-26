package com.nimbletech.petadopt.pet;

import java.util.Optional;

public interface PetApi {
    Optional<Pet> findById(String petId);

    Pet save(Pet pet);

    Optional<Pet> findByName(String name);

    long count();
}
