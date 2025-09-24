package com.nimbletech.petadopt.adoption.mapper;

import com.nimbletech.petadopt.adoption.dto.AdoptionRequestResponseDTO;
import com.nimbletech.petadopt.adoption.model.AdoptionRequest;
import com.nimbletech.petadopt.adoption.model.AdoptionStatus;
import com.nimbletech.petadopt.person.model.Person;
import com.nimbletech.petadopt.pet.model.Pet;

import java.time.LocalDateTime;

public class AdoptionRequestMapper {

    public static AdoptionRequest toEntity(Person person, Pet pet) {
        AdoptionRequest ar = new AdoptionRequest();
        ar.setStatus(AdoptionStatus.PENDING);
        ar.setRequestDate(LocalDateTime.now());
        ar.setPet(pet);
        ar.setPerson(person);
        return ar;
    }

    public static AdoptionRequestResponseDTO toDto(AdoptionRequest entity) {
        return new AdoptionRequestResponseDTO(
                entity.getId(),
                entity.getPet().getId(),
                entity.getPerson().getId(),
                entity.getStatus(),
                entity.getRequestDate()
        );
    }
}
