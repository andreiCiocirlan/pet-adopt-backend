package com.nimbletech.petadopt.appointment.service;

import com.nimbletech.petadopt.Query;
import com.nimbletech.petadopt.appointment.dto.AppointmentDto;
import com.nimbletech.petadopt.appointment.mapper.AppointmentMapper;
import com.nimbletech.petadopt.appointment.model.Appointment;
import com.nimbletech.petadopt.appointment.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetAppointmentsByUserIdService implements Query<Long, List<AppointmentDto>> {

    private final AppointmentRepository appointmentRepository;

    @Override
    public ResponseEntity<List<AppointmentDto>> execute(Long userId) {
        log.info("Getting appointment for userId={}", userId);
        List<Appointment> appointments = appointmentRepository.findByUserId(userId);

        List<AppointmentDto> appointmentsDto = appointments.stream()
                .map(AppointmentMapper::toDto)
                .toList();

        return ResponseEntity.ok(appointmentsDto);
    }
}
