package com.nimbletech.petadopt.notification.domain.models;

import org.springframework.modulith.NamedInterface;

import java.time.LocalDateTime;

@NamedInterface
public record AppointmentStatusChangedEvent(
        Long appointmentId,
        Long userId,
        String status,
        String message,
        LocalDateTime timestamp
) {
}
