package com.nimbletech.petadopt.pet.controller;

import com.nimbletech.petadopt.pet.dto.CreatePetDto;
import com.nimbletech.petadopt.pet.dto.PetDto;
import com.nimbletech.petadopt.pet.dto.PetUpdateRequest;
import com.nimbletech.petadopt.pet.dto.UpdatePetDto;
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

    private final GetPetsService getPetsService;
    private final CreatePetService createPetService;
    private final UpdatePetService updatePetService;
    private final DeletePetService deletePetService;
    private final GetPetByIdService getPetByIdService;

    @GetMapping
    public ResponseEntity<List<PetDto>> getAllPets() {
        return getPetsService.execute(null);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable String id) {
        return deletePetService.execute(id);
    }
}
