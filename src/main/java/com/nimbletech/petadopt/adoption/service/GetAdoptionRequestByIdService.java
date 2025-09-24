package com.nimbletech.petadopt.adoption.service;

import com.nimbletech.petadopt.Query;
import com.nimbletech.petadopt.adoption.dto.AdoptionRequestResponseDTO;
import com.nimbletech.petadopt.adoption.mapper.AdoptionRequestMapper;
import com.nimbletech.petadopt.adoption.repository.AdoptionRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetAdoptionRequestByIdService implements Query<Long, AdoptionRequestResponseDTO> {

    private final AdoptionRequestRepository adoptionRequestRepository;

    @Override
    public ResponseEntity<AdoptionRequestResponseDTO> execute(Long id) {
        log.info("Executing {}", getClass().getSimpleName());
        return adoptionRequestRepository.findById(id)
                .map(AdoptionRequestMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
