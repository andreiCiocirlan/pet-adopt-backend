package com.nimbletech.petadopt.pet.domain;

import com.nimbletech.petadopt.common.Command;
import com.nimbletech.petadopt.pet.Pet;
import com.nimbletech.petadopt.pet.domain.models.PetDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdoptPetService implements Command<String, PetDto> {

    private final PetRepository petRepository;

    @Override
    @Transactional
    public ResponseEntity<PetDto> execute(String petId) {
        log.info("Pet with id={} was adopted", petId);
        return petRepository.findByIdWithAssociations(petId)
                .map(existingPet -> {
                    existingPet.setStatus(PetStatus.ADOPTED);
                    Pet updated = petRepository.save(existingPet);
                    return ResponseEntity.ok(PetMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}