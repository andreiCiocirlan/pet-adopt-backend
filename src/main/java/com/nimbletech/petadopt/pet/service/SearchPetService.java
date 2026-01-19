package com.nimbletech.petadopt.pet.service;

import com.nimbletech.petadopt.Query;
import com.nimbletech.petadopt.pet.dto.PetDto;
import com.nimbletech.petadopt.pet.dto.PetSearchRequest;
import com.nimbletech.petadopt.pet.mapper.PetMapper;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class SearchPetService implements Query<PetSearchRequest, Page<PetDto>> {

    private final PetRepository petRepository;

    @Override
    public ResponseEntity<Page<PetDto>> execute(PetSearchRequest request) {
        log.info("Searching pets with filters: animalType={}, breed={}, age={}, clinicId={}",
                request.animalType(), request.breed(), request.age(), request.clinicId());

        Page<Pet> pets = petRepository.findPetsByFilters(request.animalType(), request.breed(), request.age(), request.status(), request.clinicId(), request.pageable());
        Page<PetDto> petDtos = pets.map(PetMapper::toDto);
        return ResponseEntity.ok(petDtos);
    }
}