package com.nimbletech.petadopt.appointment.dto;

import com.nimbletech.petadopt.appointment.model.AppointmentReason;
import com.nimbletech.petadopt.appointment.model.AppointmentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateAppointmentRequest {

    @NotNull(message = "Status must be provided")
    private AppointmentStatus status;

    private LocalDateTime appointmentDate;

    private AppointmentReason appointmentReason;
}
