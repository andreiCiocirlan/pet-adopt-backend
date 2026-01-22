package com.nimbletech.petadopt.pet.controller;

import com.nimbletech.petadopt.PaginatedResult;
import com.nimbletech.petadopt.pet.dto.*;
import com.nimbletech.petadopt.pet.model.AnimalType;
import com.nimbletech.petadopt.pet.model.PetStatus;
import com.nimbletech.petadopt.pet.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

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
    private final SetClinicForPetService setClinicForPetService;
    private final RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<PaginatedResult<PetDto>> searchPets(
            @RequestParam(required = false) AnimalType animalType,
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) PetStatus status,
            @RequestParam(required = false) String clinicId,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        PetSearchRequest searchRequest = new PetSearchRequest(animalType, breed, age, status, clinicId, pageable);
        return searchPetService.execute(searchRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDto> getPetById(@PathVariable String id) {
        return getPetByIdService.execute(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PetDto> createPet(@RequestBody CreatePetDto petDto) {
        return createPetService.execute(petDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{petId}/clinic/{clinicId}")
    public ResponseEntity<?> setClinicForPet(@PathVariable String petId, @PathVariable String clinicId) {
        return setClinicForPetService.execute(new SetClinicForPetRequest(petId, clinicId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PetDto> updatePet(@PathVariable String id, @RequestBody UpdatePetDto petDto) {
        PetUpdateRequest updateRequest = new PetUpdateRequest(id, petDto);
        return updatePetService.execute(updateRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/adopt/{id}")
    public ResponseEntity<PetDto> adoptPet(@PathVariable String id) {
        return adoptPetService.execute(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable String id) {
        return deletePetService.execute(id);
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> downloadImage(@RequestParam("url") String imageUrl) throws IOException {
        // Fetch the image from the external URL
        byte[] imageBytes = restTemplate.getForObject(imageUrl, byte[].class);

        // Retrieve content type from the response (optional, for accuracy)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Default fallback
        headers.setContentLength(imageBytes.length);

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
