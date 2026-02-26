package com.nimbletech.petadopt.appointment.domain.models;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AppointmentDto(
        Long id,
        String petId,
        Long userId,
        LocalDateTime appointmentDateTime,
        String status,
        String appointmentReason
) {
}