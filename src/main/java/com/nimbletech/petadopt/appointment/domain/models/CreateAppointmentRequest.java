package com.nimbletech.petadopt.appointment.domain.models;

import com.nimbletech.petadopt.appointment.domain.AppointmentReason;
import com.nimbletech.petadopt.appointment.domain.AppointmentStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreateAppointmentRequest(
        String petId,
        Long userId,
        LocalDateTime appointmentDateTime,
        AppointmentReason appointmentReason,
        AppointmentStatus appointmentStatus
) {
}