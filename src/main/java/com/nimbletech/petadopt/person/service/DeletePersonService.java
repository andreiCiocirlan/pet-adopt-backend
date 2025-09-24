package com.nimbletech.petadopt.person.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeletePersonService implements Command<Long, Void> {

    private final PersonRepository personRepository;

    @Override
    public ResponseEntity<Void> execute(Long id) {
        log.info("Executing {}", getClass().getSimpleName());
        if (!personRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        personRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
