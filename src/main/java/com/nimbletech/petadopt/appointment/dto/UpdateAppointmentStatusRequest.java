package com.nimbletech.petadopt.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateAppointmentStatusRequest {
    private Long appointmentId;
    private AppointmentStatusRequest updateAppointmentRequest;

}