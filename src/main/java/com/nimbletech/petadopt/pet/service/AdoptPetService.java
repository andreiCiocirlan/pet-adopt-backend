package com.nimbletech.petadopt.pet.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.pet.dto.PetDto;
import com.nimbletech.petadopt.pet.mapper.PetMapper;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.pet.model.PetStatus;
import com.nimbletech.petadopt.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdoptPetService implements Command<String, PetDto> {

    private final PetRepository petRepository;

    @Override
    public ResponseEntity<PetDto> execute(String petId) {
        log.info("Pet with id={} was adopted", petId);
        return petRepository.findById(petId)
                .map(existingPet -> {
                    existingPet.setStatus(PetStatus.ADOPTED);
                    Pet updated = petRepository.save(existingPet);
                    return ResponseEntity.ok(PetMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}