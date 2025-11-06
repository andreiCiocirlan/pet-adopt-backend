package com.nimbletech.petadopt.pet.repository;

import com.nimbletech.petadopt.pet.model.AnimalType;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.pet.model.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, String> {

    @Query("SELECT p FROM Pet p LEFT JOIN FETCH p.clinic c WHERE "
           + "(:animalType IS NULL OR p.type = :animalType) AND "
           + "(LOWER(p.breed) LIKE LOWER(CONCAT('%', COALESCE(:breed, ''), '%'))) AND "
           + "(:age IS NULL OR p.age = :age) AND "
           + "(:status IS NULL OR p.status = :status) AND "
           + "(:clinicId IS NULL OR c.id = :clinicId)")
    List<Pet> findPetsByFilters(@Param("animalType") AnimalType animalType,
                                @Param("breed") String breed,
                                @Param("age") Integer age,
                                @Param("status") PetStatus status,
                                @Param("clinicId") String clinicId);
}