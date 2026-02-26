package com.nimbletech.petadopt.pet.service;

import com.nimbletech.petadopt.clinic.ClinicApi;
import com.nimbletech.petadopt.common.Command;
import com.nimbletech.petadopt.clinic.model.Clinic;
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

    private final ClinicApi clinicApi;
    private final PetRepository petRepository;

    @Override
    public ResponseEntity<PetDto> execute(SetClinicForPetRequest request) {
        log.info("Setting pet with id={} to clinicId={}", request.petId(), request.clinicId());
        Pet pet = petRepository.findById(request.petId())
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));

        Clinic clinic = clinicApi.findById(request.clinicId())
                .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));

        pet.setClinic(clinic);
        Pet saved = petRepository.save(pet);

        return ResponseEntity.ok(PetMapper.toDto(saved));
    }
}
