package com.nimbletech.petadopt.adoption.service;

import com.nimbletech.petadopt.Query;
import com.nimbletech.petadopt.adoption.dto.AdoptionRequestResponseDTO;
import com.nimbletech.petadopt.adoption.mapper.AdoptionRequestMapper;
import com.nimbletech.petadopt.adoption.model.AdoptionRequest;
import com.nimbletech.petadopt.adoption.repository.AdoptionRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetUserAdoptionsService implements Query<Long, List<AdoptionRequestResponseDTO>> {

    private final AdoptionRequestRepository adoptionRequestRepository;

    @Override
    public ResponseEntity<List<AdoptionRequestResponseDTO>> execute(Long userId) {
        log.info("Executing {}", getClass().getSimpleName());
        List<AdoptionRequest> requests = adoptionRequestRepository.findByUserId(userId);

        List<AdoptionRequestResponseDTO> dtoList = requests.stream()
                .map(AdoptionRequestMapper::toDto)
                .toList();

        return ResponseEntity.ok(dtoList);
    }
}
