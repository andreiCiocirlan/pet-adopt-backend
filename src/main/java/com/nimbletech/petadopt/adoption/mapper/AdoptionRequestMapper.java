package com.nimbletech.petadopt.adoption.mapper;

import com.nimbletech.petadopt.adoption.dto.AdoptionRequestResponseDTO;
import com.nimbletech.petadopt.adoption.model.AdoptionRequest;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.user.model.User;

public class AdoptionRequestMapper {

    public static AdoptionRequest toEntity(User user, Pet pet) {
        AdoptionRequest ar = new AdoptionRequest();
        ar.setPet(pet);
        ar.setUser(user);
        return ar;
    }

    public static AdoptionRequestResponseDTO toDto(AdoptionRequest entity) {
        return new AdoptionRequestResponseDTO(
                entity.getId(),
                entity.getPet().getId(),
                entity.getUser().getId(),
                entity.getStatus(),
                entity.getRequestDate()
        );
    }
}
