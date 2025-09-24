package com.nimbletech.petadopt.adoption.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.adoption.dto.AdoptionRequestResponseDTO;
import com.nimbletech.petadopt.adoption.dto.AdoptionRequestUpdateRequest;
import com.nimbletech.petadopt.adoption.dto.UpdateAdoptionRequestDto;
import com.nimbletech.petadopt.adoption.mapper.AdoptionRequestMapper;
import com.nimbletech.petadopt.adoption.model.AdoptionRequest;
import com.nimbletech.petadopt.adoption.model.AdoptionStatus;
import com.nimbletech.petadopt.adoption.repository.AdoptionRequestRepository;
import com.nimbletech.petadopt.person.model.Person;
import com.nimbletech.petadopt.person.model.PersonStatus;
import com.nimbletech.petadopt.person.repository.PersonRepository;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.pet.model.PetStatus;
import com.nimbletech.petadopt.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UpdateAdoptionRequestService implements Command<AdoptionRequestUpdateRequest, AdoptionRequestResponseDTO> {

    private final AdoptionRequestRepository adoptionRequestRepository;
    private final PersonRepository personRepository;
    private final PetRepository petRepository;

    @Override
    public ResponseEntity<AdoptionRequestResponseDTO> execute(AdoptionRequestUpdateRequest updateRequest) {
        log.info("Executing {}", getClass().getSimpleName());
        Long id = updateRequest.getId();
        UpdateAdoptionRequestDto dto = updateRequest.getDto();

        return adoptionRequestRepository.findById(id)
                .map(existing -> {
                    if (AdoptionStatus.APPROVED == dto.getStatus()) {
                        Person person = existing.getPerson();
                        Pet pet = existing.getPet();

                        person.setStatus(PersonStatus.ADOPTER);
                        pet.setStatus(PetStatus.ADOPTED);

                        personRepository.save(person);
                        petRepository.save(pet);
                    }

                    existing.setStatus(dto.getStatus());
                    AdoptionRequest updated = adoptionRequestRepository.save(existing);
                    return ResponseEntity.ok(AdoptionRequestMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
