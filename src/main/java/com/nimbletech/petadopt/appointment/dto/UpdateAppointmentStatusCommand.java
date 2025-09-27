package com.nimbletech.petadopt.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateAppointmentStatusCommand {
    private Long appointmentId;
    private UpdateAppointmentRequest updateAppointmentRequest;

}