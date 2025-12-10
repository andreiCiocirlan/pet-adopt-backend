package com.nimbletech.petadopt.appointment.service;

import com.nimbletech.petadopt.Query;
import com.nimbletech.petadopt.appointment.dto.AppointmentDto;
import com.nimbletech.petadopt.appointment.mapper.AppointmentMapper;
import com.nimbletech.petadopt.appointment.model.Appointment;
import com.nimbletech.petadopt.appointment.model.AppointmentStatus;
import com.nimbletech.petadopt.appointment.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetAppointmentsService implements Query<List<AppointmentStatus>, List<AppointmentDto>> {

    private final AppointmentRepository appointmentRepository;

    @Override
    public ResponseEntity<List<AppointmentDto>> execute(@Nullable List<AppointmentStatus> statuses) {
        log.info("Getting appointments with optional status filter");
        List<Appointment> appointments;

        if (statuses == null || statuses.isEmpty()) {
            appointments = appointmentRepository.findAll();
        } else {
            appointments = appointmentRepository.findByStatusIn(statuses);
        }

        List<AppointmentDto> appointmentDtos = appointments.stream()
                .map(AppointmentMapper::toDto)
                .toList();
        return ResponseEntity.ok(appointmentDtos);
    }
}
