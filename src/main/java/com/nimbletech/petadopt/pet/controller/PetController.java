package com.nimbletech.petadopt.pet.controller;

import com.nimbletech.petadopt.pet.dto.*;
import com.nimbletech.petadopt.pet.model.AnimalType;
import com.nimbletech.petadopt.pet.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final CreatePetService createPetService;
    private final UpdatePetService updatePetService;
    private final DeletePetService deletePetService;
    private final GetPetByIdService getPetByIdService;
    private final SearchPetService searchPetService;
    private final AdoptPetService adoptPetService;

    @GetMapping
    public ResponseEntity<List<PetDto>> searchPets(
            @RequestParam String animalType,
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) Integer age) {
        try {
            PetSearchRequest searchRequest = new PetSearchRequest(AnimalType.valueOf(animalType.toUpperCase()), breed, age);
            return searchPetService.execute(searchRequest);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDto> getPetById(@PathVariable String id) {
        return getPetByIdService.execute(id);
    }

    @PostMapping
    public ResponseEntity<PetDto> createPet(@RequestBody CreatePetDto petDto) {
        return createPetService.execute(petDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDto> updatePet(@PathVariable String id, @RequestBody UpdatePetDto petDto) {
        PetUpdateRequest updateRequest = new PetUpdateRequest(id, petDto);
        return updatePetService.execute(updateRequest);
    }

    @PutMapping("/adopt/{id}")
    public ResponseEntity<PetDto> adoptPet(@PathVariable String id) {
        return adoptPetService.execute(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable String id) {
        return deletePetService.execute(id);
    }
}
