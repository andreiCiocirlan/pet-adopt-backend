package com.nimbletech.petadopt.appointment.dto;

public record UpdateAppointmentStatusRequest(
        Long appointmentId,
        AppointmentStatusRequest updateAppointmentRequest
) {
}