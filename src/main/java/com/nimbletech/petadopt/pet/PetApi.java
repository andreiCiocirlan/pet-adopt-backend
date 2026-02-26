package com.nimbletech.petadopt.pet;

import com.nimbletech.petadopt.pet.domain.Pet;
import com.nimbletech.petadopt.pet.domain.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetApi {
    private final PetRepository petRepository;
    
    public Optional<Pet> findById(String petId) {
        return petRepository.findById(petId);
    }

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }
}
