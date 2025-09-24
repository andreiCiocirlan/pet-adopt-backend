package com.nimbletech.petadopt.person.controller;

import com.nimbletech.petadopt.person.dto.CreatePersonDto;
import com.nimbletech.petadopt.person.dto.PersonDto;
import com.nimbletech.petadopt.person.dto.PersonUpdateRequest;
import com.nimbletech.petadopt.person.dto.UpdatePersonDto;
import com.nimbletech.petadopt.person.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final GetPersonsService getPersonsService;
    private final GetPersonByIdService getPersonByIdService;
    private final CreatePersonService createPersonService;
    private final UpdatePersonService updatePersonService;
    private final DeletePersonService deletePersonService;

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAllPersons() {
        return getPersonsService.execute(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable Long id) {
        return getPersonByIdService.execute(id);
    }

    @PostMapping
    public ResponseEntity<PersonDto> createPerson(@RequestBody CreatePersonDto dto) {
        return createPersonService.execute(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable Long id, @RequestBody UpdatePersonDto dto) {
        PersonUpdateRequest updateRequest = new PersonUpdateRequest(id, dto);
        return updatePersonService.execute(updateRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        return deletePersonService.execute(id);
    }
}
