package com.nimbletech.petadopt.pet.service;

import com.nimbletech.petadopt.common.Command;
import com.nimbletech.petadopt.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeletePetService implements Command<String, Void> {

    private final PetRepository petRepository;

    @Override
    public ResponseEntity<Void> execute(String id) {
        log.info("Deleting pet with id={} ", id);
        if (!petRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        petRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}