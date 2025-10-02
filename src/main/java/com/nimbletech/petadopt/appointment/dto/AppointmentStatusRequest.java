package com.nimbletech.petadopt.appointment.dto;

import com.nimbletech.petadopt.appointment.model.AppointmentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentStatusRequest {
    @NotNull(message = "Status must be provided")
    private AppointmentStatus status;
}
