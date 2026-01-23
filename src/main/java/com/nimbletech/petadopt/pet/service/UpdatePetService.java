package com.nimbletech.petadopt.pet.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.pet.dto.PetDto;
import com.nimbletech.petadopt.pet.dto.PetUpdateRequest;
import com.nimbletech.petadopt.pet.mapper.PetMapper;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.pet.repository.PetRepository;
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