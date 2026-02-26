package com.nimbletech.petadopt.pet.domain;

import com.nimbletech.petadopt.clinic.ClinicApi;
import com.nimbletech.petadopt.common.Command;
import com.nimbletech.petadopt.clinic.Clinic;
import com.nimbletech.petadopt.pet.domain.models.CreatePetDto;
import com.nimbletech.petadopt.pet.domain.models.PetDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreatePetService implements Command<CreatePetDto, PetDto> {

    private final PetRepository petRepository;
    private final ClinicApi clinicApi;

    @Override
    public ResponseEntity<PetDto> execute(CreatePetDto petDto) {
        log.info("Creating pet with name={}, breed={}, clinicId={}", petDto.name(), petDto.breed(), petDto.clinicId());
        Pet pet = PetMapper.toEntity(petDto);
        Clinic clinic = clinicApi.findById(petDto.clinicId())
                .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));
        pet.setStatus(PetStatus.AVAILABLE);
        pet.setClinic(clinic);
        Pet saved = petRepository.save(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(PetMapper.toDto(saved));
    }
}
