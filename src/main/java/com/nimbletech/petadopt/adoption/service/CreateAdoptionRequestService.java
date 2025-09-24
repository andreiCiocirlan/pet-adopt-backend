package com.nimbletech.petadopt.adoption.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.adoption.dto.AdoptionRequestCreateDTO;
import com.nimbletech.petadopt.adoption.dto.AdoptionRequestResponseDTO;
import com.nimbletech.petadopt.adoption.mapper.AdoptionRequestMapper;
import com.nimbletech.petadopt.adoption.model.AdoptionRequest;
import com.nimbletech.petadopt.adoption.repository.AdoptionRequestRepository;
import com.nimbletech.petadopt.person.model.Person;
import com.nimbletech.petadopt.person.repository.PersonRepository;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.pet.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateAdoptionRequestService implements Command<AdoptionRequestCreateDTO, AdoptionRequestResponseDTO> {

    private final AdoptionRequestRepository adoptionRequestRepository;
    private final PersonRepository personRepository;
    private final PetRepository petRepository;

    @Override
    public ResponseEntity<AdoptionRequestResponseDTO> execute(AdoptionRequestCreateDTO dto) {
        log.info("Executing {}", getClass().getSimpleName());

        Person person = personRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));

        Pet pet = petRepository.findById(dto.getPetId())
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));

        // Check preconditions, e.g., pet availability and person status
        if (!"available".equalsIgnoreCase(pet.getStatus())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(null); // or custom error dto/message
        }

        AdoptionRequest request = AdoptionRequestMapper.toEntity(person, pet);
        request.setStatus("pending");
        request.setRequestDate(LocalDateTime.now());

        AdoptionRequest savedRequest = adoptionRequestRepository.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AdoptionRequestMapper.toDto(savedRequest));
    }
}

