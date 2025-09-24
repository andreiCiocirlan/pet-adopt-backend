package com.nimbletech.petadopt.pet.service;

import com.nimbletech.petadopt.Query;
import com.nimbletech.petadopt.pet.dto.PetDto;
import com.nimbletech.petadopt.pet.mapper.PetMapper;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetPetsService implements Query<Void, List<PetDto>> {

    private final PetRepository petRepository;

    @Override
    public ResponseEntity<List<PetDto>> execute(Void input) {
        log.info("Executing {} ", getClass().getSimpleName());
        List<Pet> products = petRepository.findAll();
        return ResponseEntity.ok(products.stream()
                .map(PetMapper::toDto)
                .toList());
    }
}
