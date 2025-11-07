package com.nimbletech.petadopt.appointment.dto;

import com.nimbletech.petadopt.appointment.model.AppointmentReason;
import com.nimbletech.petadopt.appointment.model.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
public class CreateAppointmentRequest {
    private String petId;
    private Long userId;
    private LocalDateTime appointmentDateTime;
    private AppointmentReason appointmentReason;
    private AppointmentStatus appointmentStatus;

}