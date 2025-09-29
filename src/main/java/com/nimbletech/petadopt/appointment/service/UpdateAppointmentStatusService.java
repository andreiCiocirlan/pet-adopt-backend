package com.nimbletech.petadopt.appointment.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.appointment.dto.AppointmentDto;
import com.nimbletech.petadopt.appointment.dto.UpdateAppointmentStatusCommand;
import com.nimbletech.petadopt.appointment.mapper.AppointmentMapper;
import com.nimbletech.petadopt.appointment.model.Appointment;
import com.nimbletech.petadopt.appointment.repository.AppointmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UpdateAppointmentStatusService implements Command<UpdateAppointmentStatusCommand, AppointmentDto> {

    private final AppointmentRepository appointmentRepository;

    @Override
    public ResponseEntity<AppointmentDto> execute(UpdateAppointmentStatusCommand cmd) {
        log.info("Executing {}", getClass().getSimpleName());
        Appointment appointment = appointmentRepository.findById(cmd.getAppointmentId())
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        appointment.setStatus(cmd.getUpdateAppointmentRequest().getStatus());
        appointment = appointmentRepository.save(appointment);
        return ResponseEntity.ok(AppointmentMapper.toDto(appointment));
    }
}
