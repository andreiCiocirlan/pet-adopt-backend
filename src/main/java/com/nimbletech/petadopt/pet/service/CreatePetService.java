package com.nimbletech.petadopt.pet.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.clinic.model.Clinic;
import com.nimbletech.petadopt.clinic.repository.ClinicRepository;
import com.nimbletech.petadopt.pet.dto.CreatePetDto;
import com.nimbletech.petadopt.pet.dto.PetDto;
import com.nimbletech.petadopt.pet.mapper.PetMapper;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.pet.model.PetStatus;
import com.nimbletech.petadopt.pet.repository.PetRepository;
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
        private final ClinicRepository clinicRepository;

        @Override
        public ResponseEntity<PetDto> execute(CreatePetDto petDto) {
            log.info("Creating pet with name={}, breed={}, clinicId={}", petDto.name(), petDto.breed(), petDto.clinicId());
            Pet pet = PetMapper.toEntity(petDto);
            Clinic clinic = clinicRepository.findById(petDto.clinicId())
                            .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));
            pet.setStatus(PetStatus.AVAILABLE);
            pet.setClinic(clinic);
            Pet saved = petRepository.save(pet);
            return ResponseEntity.status(HttpStatus.CREATED).body(PetMapper.toDto(saved));
        }
}
