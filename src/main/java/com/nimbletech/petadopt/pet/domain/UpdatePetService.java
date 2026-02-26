package com.nimbletech.petadopt.pet.domain;

import com.nimbletech.petadopt.common.Command;
import com.nimbletech.petadopt.pet.domain.models.PetDto;
import com.nimbletech.petadopt.pet.domain.models.PetUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdatePetService implements Command<PetUpdateRequest, PetDto> {

    private final PetRepository petRepository;

    @Override
    public ResponseEntity<PetDto> execute(PetUpdateRequest updateRequest) {
        log.info("Updating user with id={}", updateRequest.id());
        return petRepository.findById(updateRequest.id())
            .map(existingPet -> {
                existingPet.setName(updateRequest.updatePetDto().name());
                existingPet.setAge(updateRequest.updatePetDto().age());
                existingPet.setType(updateRequest.updatePetDto().type());
                existingPet.setBreed(updateRequest.updatePetDto().breed());
                existingPet.setHealth(updateRequest.updatePetDto().health());
                existingPet.setCharacteristics(updateRequest.updatePetDto().characteristics());
                existingPet.setImageUrls(updateRequest.updatePetDto().imageUrls());
                Pet updated = petRepository.save(existingPet);
                return ResponseEntity.ok(PetMapper.toDto(updated));
            })
            .orElse(ResponseEntity.notFound().build());
    }
}