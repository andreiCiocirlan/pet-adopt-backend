package com.nimbletech.petadopt.person.mapper;

import com.nimbletech.petadopt.person.dto.CreatePersonDto;
import com.nimbletech.petadopt.person.dto.PersonDto;
import com.nimbletech.petadopt.person.model.Person;
import com.nimbletech.petadopt.person.model.PersonStatus;

public class PersonMapper {

    public static PersonDto toDto(Person person) {
        if (person == null) return null;
        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .email(person.getEmail())
                .phone(person.getPhone())
                .address(person.getAddress())
                .status(person.getStatus())
                .build();
    }

    public static Person toEntity(CreatePersonDto dto) {
        if (dto == null) return null;
        Person person = new Person();
        person.setName(dto.getName());
        person.setEmail(dto.getEmail());
        person.setPhone(dto.getPhone());
        person.setAddress(dto.getAddress());
        person.setStatus(PersonStatus.APPLICANT);
        return person;
    }
}
