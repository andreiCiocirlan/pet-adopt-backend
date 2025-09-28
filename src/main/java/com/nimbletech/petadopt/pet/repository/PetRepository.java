package com.nimbletech.petadopt.pet.repository;

import com.nimbletech.petadopt.pet.model.AnimalType;
import com.nimbletech.petadopt.pet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, String> {

    @Query("SELECT p FROM Pet p LEFT JOIN FETCH p.clinic WHERE "
           + "(:animalType IS NULL OR p.type = :animalType) AND "
           + "(LOWER(p.breed) LIKE LOWER(CONCAT('%', COALESCE(:breed, ''), '%'))) AND "
           + "(:age IS NULL OR p.age = :age)")
    List<Pet> findPetsByFilters(@Param("animalType") AnimalType animalType,
                                @Param("breed") String breed,
                                @Param("age") Integer age);
}