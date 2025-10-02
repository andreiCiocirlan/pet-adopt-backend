package com.nimbletech.petadopt.pet.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.clinic.model.Clinic;
import com.nimbletech.petadopt.clinic.repository.ClinicRepository;
import com.nimbletech.petadopt.pet.dto.PetDto;
import com.nimbletech.petadopt.pet.dto.SetClinicForPetRequest;
import com.nimbletech.petadopt.pet.mapper.PetMapper;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.pet.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SetClinicForPetService implements Command<SetClinicForPetRequest, PetDto> {

    private final ClinicRepository clinicRepository;
    private final PetRepository petRepository;

    @Override
    public ResponseEntity<PetDto> execute(SetClinicForPetRequest request) {
        log.info("Setting pet with id={} to clinicId={}", request.getPetId(), request.getClinicId());
        Pet pet = petRepository.findById(request.getPetId())
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));

        Clinic clinic = clinicRepository.findById(request.getClinicId())
                .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));

        pet.setClinic(clinic);
        Pet saved = petRepository.save(pet);

        return ResponseEntity.ok(PetMapper.toDto(saved));
    }
}
