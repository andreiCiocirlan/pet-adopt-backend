package com.nimbletech.petadopt.pet.dto;

import java.util.List;

public record PaginatedPetsResponse(
    List<PetDto> content,
    int page,
    int size, 
    long totalElements,
    int totalPages
) {}