package com.nimbletech.petadopt.adoption.dto;

import java.time.LocalDateTime;

public record AdoptionRequestResponseDTO(Long id, Long petId, Long personId, String status, LocalDateTime requestDate) {}
