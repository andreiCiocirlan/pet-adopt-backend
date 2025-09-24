package com.nimbletech.petadopt.repository;

import com.nimbletech.petadopt.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}