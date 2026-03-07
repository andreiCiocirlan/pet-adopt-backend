package com.nimbletech.petadopt.pet.domain;

import com.nimbletech.petadopt.common.Command;
import com.nimbletech.petadopt.pet.Pet;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeletePetService implements Command<String, Void> {

    private final PetRepository petRepository;

    @Override
    public ResponseEntity<Void> execute(String id) {
        log.info("Soft-deleting pet with id={}", id);

        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));

        // Only allow removal of ADOPTED pets
        if (pet.getStatus() != PetStatus.ADOPTED) {
            log.warn("Cannot remove pet with id={} because status is {} (must be ADOPTED)", id, pet.getStatus());
            return ResponseEntity.badRequest().build();
        }

        // Update status to REMOVED
        pet.setStatus(PetStatus.REMOVED);
        petRepository.save(pet);

        log.info("Pet with id={} successfully marked as REMOVED", id);
        return ResponseEntity.noContent().build();
    }
}