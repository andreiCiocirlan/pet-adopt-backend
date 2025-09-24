package com.nimbletech.petadopt.person.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.person.dto.CreatePersonDto;
import com.nimbletech.petadopt.person.dto.PersonDto;
import com.nimbletech.petadopt.person.mapper.PersonMapper;
import com.nimbletech.petadopt.person.model.Person;
import com.nimbletech.petadopt.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreatePersonService implements Command<CreatePersonDto, PersonDto> {

    private final PersonRepository personRepository;

    @Override
    public ResponseEntity<PersonDto> execute(CreatePersonDto dto) {
        log.info("Executing {}", getClass().getSimpleName());
        Person person = PersonMapper.toEntity(dto);
        Person saved = personRepository.save(person);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(PersonMapper.toDto(saved));
    }
}
