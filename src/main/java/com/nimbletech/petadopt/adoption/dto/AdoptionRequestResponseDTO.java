package com.nimbletech.petadopt.adoption.dto;

import com.nimbletech.petadopt.adoption.model.AdoptionStatus;

import java.time.LocalDateTime;

public record AdoptionRequestResponseDTO(Long id, Long petId, Long userId, AdoptionStatus status, LocalDateTime requestDate) {}
