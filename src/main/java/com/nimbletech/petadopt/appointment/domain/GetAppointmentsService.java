package com.nimbletech.petadopt.appointment.domain;

import com.nimbletech.petadopt.common.Query;
import com.nimbletech.petadopt.appointment.domain.models.AppointmentDto;
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
