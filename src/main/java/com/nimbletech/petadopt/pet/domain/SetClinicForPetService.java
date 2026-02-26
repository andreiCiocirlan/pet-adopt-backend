package com.nimbletech.petadopt.pet.domain;

import com.nimbletech.petadopt.clinic.Clinic;
import com.nimbletech.petadopt.clinic.ClinicApi;
import com.nimbletech.petadopt.common.Command;
import com.nimbletech.petadopt.pet.Pet;
import com.nimbletech.petadopt.pet.PetApi;
import com.nimbletech.petadopt.pet.domain.models.PetDto;
import com.nimbletech.petadopt.pet.domain.models.SetClinicForPetRequest;
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
    private final PetApi petApi;

    @Override
    public ResponseEntity<PetDto> execute(SetClinicForPetRequest request) {
        log.info("Setting pet with id={} to clinicId={}", request.petId(), request.clinicId());
        Pet pet = petApi.findById(request.petId())
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));

        Clinic clinic = clinicApi.findById(request.clinicId())
                .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));

        pet.setClinic(clinic);
        Pet saved = petApi.save(pet);

        return ResponseEntity.ok(PetMapper.toDto(saved));
    }
}
