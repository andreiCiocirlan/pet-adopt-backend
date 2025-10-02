package com.nimbletech.petadopt.appointment.dto;

import com.nimbletech.petadopt.appointment.model.AppointmentReason;
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
    private LocalDateTime appointmentDate;
    private AppointmentReason appointmentReason;

}