package com.nimbletech.petadopt.pet.service;

import com.nimbletech.petadopt.Query;
import com.nimbletech.petadopt.pet.dto.PetDto;
import com.nimbletech.petadopt.pet.dto.PetSearchRequest;
import com.nimbletech.petadopt.pet.mapper.PetMapper;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class SearchPetService implements Query<PetSearchRequest, List<PetDto>> {

    private final PetRepository petRepository;

    @Override
    public ResponseEntity<List<PetDto>> execute(PetSearchRequest request) {
        log.info("Executing {} with filters: animalType={}, breed={}, age={}", getClass().getSimpleName(),
                request.animalType(), request.breed(), request.age());

        List<Pet> pets = petRepository.findPetsByFilters(request.animalType(), request.breed(), request.age());

        List<PetDto> petDtos = pets.stream()
                .map(PetMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(petDtos);
    }
}