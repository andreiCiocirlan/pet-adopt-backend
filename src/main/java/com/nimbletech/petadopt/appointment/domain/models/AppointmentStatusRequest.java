package com.nimbletech.petadopt.appointment.domain.models;

import com.nimbletech.petadopt.appointment.domain.AppointmentStatus;
import jakarta.validation.constraints.NotNull;

public record AppointmentStatusRequest(
        @NotNull(message = "Status must be provided")
        AppointmentStatus status
) {
}
