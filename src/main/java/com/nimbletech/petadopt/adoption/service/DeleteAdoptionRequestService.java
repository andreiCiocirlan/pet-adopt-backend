package com.nimbletech.petadopt.adoption.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.adoption.repository.AdoptionRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeleteAdoptionRequestService implements Command<Long, Void> {

    private final AdoptionRequestRepository adoptionRequestRepository;

    @Override
    public ResponseEntity<Void> execute(Long id) {
        log.info("Executing {}", getClass().getSimpleName());
        if (!adoptionRequestRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        adoptionRequestRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
