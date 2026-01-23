package com.nimbletech.petadopt.appointment.dto;

import com.nimbletech.petadopt.appointment.model.AppointmentReason;
import com.nimbletech.petadopt.appointment.model.AppointmentStatus;
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