package com.nimbletech.petadopt.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
public class AppointmentDto {
    private Long id;
    private String petId;
    private Long userId;
    private LocalDateTime appointmentDate;
    private String status;
    private String appointmentReason;

}