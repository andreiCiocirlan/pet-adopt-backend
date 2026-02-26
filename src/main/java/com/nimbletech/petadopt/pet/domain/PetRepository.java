package com.nimbletech.petadopt.pet.domain;

import com.nimbletech.petadopt.pet.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, String> {

    @Query("""
            SELECT DISTINCT p.id, p.type, p.name FROM Pet p
            LEFT JOIN p.clinic c
            WHERE (:animalType IS NULL OR p.type = :animalType) AND
                  (LOWER(p.breed) LIKE LOWER(CONCAT('%', COALESCE(:breed, ''), '%'))) AND
                  (:age IS NULL OR p.age = :age) AND
                  (:status IS NULL OR p.status = :status) AND
                  (:clinicId IS NULL OR c.id = :clinicId)
            ORDER BY p.type ASC, p.name ASC, p.id ASC
    """)
    Page<Object[]> findPetIdsByFilters(
            @Param("animalType") AnimalType animalType,
            @Param("breed") String breed,
            @Param("age") Integer age,
            @Param("status") PetStatus status,
            @Param("clinicId") String clinicId,
            Pageable pageable);

    @Query("""
            SELECT DISTINCT p FROM Pet p
            LEFT JOIN FETCH p.clinic
            LEFT JOIN FETCH p.imageUrls
            WHERE p.id IN :ids
    """)
    List<Pet> findPetsWithImagesByIds(@Param("ids") List<String> ids);

    @Query("SELECT p FROM Pet p LEFT JOIN FETCH p.clinic LEFT JOIN FETCH p.imageUrls WHERE p.id = :id")
    Optional<Pet> findByIdWithAssociations(@Param("id") String id);

    Optional<Pet> findByName(String name);
}