package com.nimbletech.petadopt.pet.service;

import com.nimbletech.petadopt.Query;
import com.nimbletech.petadopt.PaginatedResult;
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

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class SearchPetService implements Query<PetSearchRequest, PaginatedResult<PetDto>> {

    private final PetRepository petRepository;

    @Override
    public ResponseEntity<PaginatedResult<PetDto>> execute(PetSearchRequest request) {
        log.info("Searching pets with filters: animalType={}, status={}, breed={}, age={}, clinicId={}, pageNo={}",
                request.animalType(), request.status(), request.breed(), request.age(), request.clinicId(), request.pageable().getPageNumber());

        Page<Object[]> idTypePage = petRepository.findPetIdsByFilters(
                request.animalType(), request.breed(), request.age(),
                request.status(), request.clinicId(), request.pageable());

        List<String> petIds = idTypePage.getContent()
                .stream()
                .map(row -> (String) row[0])
                .toList();

        List<Pet> pets = petRepository.findPetsWithImagesByIds(petIds);

        Map<String, Pet> petMap = pets.stream()
                .collect(Collectors.toMap(Pet::getId, Function.identity()));

        List<Pet> orderedPets = petIds.stream()  // ← Uses EXACT petIds order
                .map(petMap::get)
                .filter(Objects::nonNull)
                .toList();

        return ResponseEntity.ok(new PaginatedResult<>(
                orderedPets.stream().map(PetMapper::toDto).collect(Collectors.toList()),
                idTypePage.getNumber(),
                idTypePage.getSize(),
                idTypePage.getTotalElements(),
                idTypePage.getTotalPages()
        ));
    }
}