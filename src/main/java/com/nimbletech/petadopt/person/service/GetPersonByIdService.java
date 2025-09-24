package com.nimbletech.petadopt.person.service;

import com.nimbletech.petadopt.Query;
import com.nimbletech.petadopt.person.dto.PersonDto;
import com.nimbletech.petadopt.person.mapper.PersonMapper;
import com.nimbletech.petadopt.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetPersonByIdService implements Query<Long, PersonDto> {

    private final PersonRepository personRepository;

    @Override
    public ResponseEntity<PersonDto> execute(Long id) {
        log.info("Executing {}", getClass().getSimpleName());
        return personRepository.findById(id)
                .map(PersonMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
