package com.nimbletech.petadopt.pet.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.pet.dto.CreatePetDto;
import com.nimbletech.petadopt.pet.dto.PetDto;
import com.nimbletech.petadopt.pet.mapper.PetMapper;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreatePetService implements Command<CreatePetDto, PetDto> {

    private final PetRepository petRepository;

    @Override
    public ResponseEntity<PetDto> execute(CreatePetDto petDto) {
        log.info("Executing {}", getClass().getSimpleName());
        Pet pet = PetMapper.toEntity(petDto);
        Pet saved = petRepository.save(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(PetMapper.toDto(saved));
    }
}
