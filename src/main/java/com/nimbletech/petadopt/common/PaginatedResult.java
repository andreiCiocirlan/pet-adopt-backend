package com.nimbletech.petadopt.common;

import java.util.List;

public record PaginatedResult<T>(
    List<T> data,
    int page,
    int size, 
    long totalElements,
    int totalPages
) {}