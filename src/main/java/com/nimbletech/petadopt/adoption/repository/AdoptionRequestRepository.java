package com.nimbletech.petadopt.adoption.repository;

import com.nimbletech.petadopt.adoption.model.AdoptionRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Long> {

    @EntityGraph(attributePaths = {"user", "pet"})
    List<AdoptionRequest> findAll();
}