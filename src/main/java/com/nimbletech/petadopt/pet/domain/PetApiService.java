package com.nimbletech.petadopt.pet.domain;

import com.nimbletech.petadopt.pet.Pet;
import com.nimbletech.petadopt.pet.PetApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class PetApiService implements PetApi {
    private final PetRepository petRepository;
    
    @Override
    public Optional<Pet> findById(String petId) {
        return petRepository.findById(petId);
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Optional<Pet> findByName(String name) {
        return petRepository.findByName(name);
    }

    @Override
    public long count() {
        return petRepository.count();
    }
}
