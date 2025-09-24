package com.nimbletech.petadopt.person.service;

import com.nimbletech.petadopt.Query;
import com.nimbletech.petadopt.person.dto.PersonDto;
import com.nimbletech.petadopt.person.mapper.PersonMapper;
import com.nimbletech.petadopt.person.model.Person;
import com.nimbletech.petadopt.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetPersonsService implements Query<Void, List<PersonDto>> {

    private final PersonRepository personRepository;

    @Override
    public ResponseEntity<List<PersonDto>> execute(Void input) {
        log.info("Executing {}", getClass().getSimpleName());
        List<Person> persons = personRepository.findAll();
        List<PersonDto> dtoList = persons.stream()
                                        .map(PersonMapper::toDto)
                                        .toList();
        return ResponseEntity.ok(dtoList);
    }
}
