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
        log.info("Executing {} ", getClass().getSimpleName());
        return petRepository.findById(updateRequest.getId())
            .map(existingPet -> {
                existingPet.setName(updateRequest.getUpdatePetDto().getName());
                existingPet.setAge(updateRequest.getUpdatePetDto().getAge());
                existingPet.setType(updateRequest.getUpdatePetDto().getType());
                existingPet.setBreed(updateRequest.getUpdatePetDto().getBreed());
                existingPet.setMedicalHistory(updateRequest.getUpdatePetDto().getMedicalHistory());
                existingPet.setMicrochipId(updateRequest.getUpdatePetDto().getMicrochipId());
                existingPet.setStatus(updateRequest.getUpdatePetDto().getStatus());
                Pet updated = petRepository.save(existingPet);
                return ResponseEntity.ok(PetMapper.toDto(updated));
            })
            .orElse(ResponseEntity.notFound().build());
    }
}