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
public class GetAppointmentsService implements Query<Void, List<AppointmentDto>> {

    private final AppointmentRepository appointmentRepository;

    @Override
    public ResponseEntity<List<AppointmentDto>> execute(Void input) {
        log.info("Executing {}", getClass().getSimpleName());
        List<Appointment> appointments = appointmentRepository.findAll();
        List<AppointmentDto> dtoList = appointments.stream()
                .map(AppointmentMapper::toDto)
                .toList();
        return ResponseEntity.ok(dtoList);
    }
}
