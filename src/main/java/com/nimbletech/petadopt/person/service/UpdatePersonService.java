package com.nimbletech.petadopt.person.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.person.dto.PersonDto;
import com.nimbletech.petadopt.person.dto.PersonUpdateRequest;
import com.nimbletech.petadopt.person.dto.UpdatePersonDto;
import com.nimbletech.petadopt.person.mapper.PersonMapper;
import com.nimbletech.petadopt.person.model.Person;
import com.nimbletech.petadopt.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UpdatePersonService implements Command<PersonUpdateRequest, PersonDto> {

    private final PersonRepository personRepository;

    @Override
    public ResponseEntity<PersonDto> execute(PersonUpdateRequest updateRequest) {
        Long id = updateRequest.getId();
        UpdatePersonDto dto = updateRequest.getUpdatePersonDto();
        log.info("Executing {}", getClass().getSimpleName());

        return personRepository.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setEmail(dto.getEmail());
                    existing.setPhone(dto.getPhone());
                    existing.setAddress(dto.getAddress());
                    existing.setStatus(dto.getStatus());
                    Person updated = personRepository.save(existing);
                    return ResponseEntity.ok(PersonMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
