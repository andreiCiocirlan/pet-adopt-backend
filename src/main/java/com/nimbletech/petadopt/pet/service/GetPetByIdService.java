package com.nimbletech.petadopt.pet.service;

import com.nimbletech.petadopt.Query;
import com.nimbletech.petadopt.pet.dto.PetDto;
import com.nimbletech.petadopt.pet.mapper.PetMapper;
import com.nimbletech.petadopt.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetPetByIdService implements Query<String, PetDto> {

    private final PetRepository petRepository;

    @Override
    public ResponseEntity<PetDto> execute(String id) {
        log.info("Executing {} ", getClass().getSimpleName());
        return petRepository.findById(id)
            .map(PetMapper::toDto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}