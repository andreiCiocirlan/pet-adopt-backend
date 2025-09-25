package com.nimbletech.petadopt.adoption.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.adoption.dto.AdoptionRequestCreateDTO;
import com.nimbletech.petadopt.adoption.dto.AdoptionRequestResponseDTO;
import com.nimbletech.petadopt.adoption.mapper.AdoptionRequestMapper;
import com.nimbletech.petadopt.adoption.model.AdoptionRequest;
import com.nimbletech.petadopt.adoption.model.AdoptionStatus;
import com.nimbletech.petadopt.adoption.repository.AdoptionRequestRepository;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.pet.model.PetStatus;
import com.nimbletech.petadopt.pet.repository.PetRepository;
import com.nimbletech.petadopt.user.model.User;
import com.nimbletech.petadopt.user.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Override
    public ResponseEntity<AdoptionRequestResponseDTO> execute(AdoptionRequestCreateDTO dto) {
        log.info("Executing {}", getClass().getSimpleName());

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Pet pet = petRepository.findById(dto.getPetId())
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));

        if (PetStatus.AVAILABLE != pet.getStatus()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(null); // or custom error dto/message
        }

        AdoptionRequest request = AdoptionRequestMapper.toEntity(user, pet);
        request.setStatus(AdoptionStatus.PENDING);
        request.setRequestDate(LocalDateTime.now());

        AdoptionRequest savedRequest = adoptionRequestRepository.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AdoptionRequestMapper.toDto(savedRequest));
    }
}

