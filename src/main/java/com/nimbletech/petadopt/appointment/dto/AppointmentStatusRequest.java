package com.nimbletech.petadopt.appointment.dto;

import com.nimbletech.petadopt.appointment.model.AppointmentStatus;
import jakarta.validation.constraints.NotNull;

public record AppointmentStatusRequest(
        @NotNull(message = "Status must be provided")
        AppointmentStatus status
) {
}
