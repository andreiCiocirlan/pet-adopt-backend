package com.nimbletech.petadopt.appointment.domain.models;

public record UpdateAppointmentStatusRequest(
        Long appointmentId,
        AppointmentStatusRequest updateAppointmentRequest
) {
}