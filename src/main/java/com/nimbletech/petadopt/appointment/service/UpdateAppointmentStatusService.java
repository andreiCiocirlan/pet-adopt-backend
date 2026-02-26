package com.nimbletech.petadopt.appointment.service;

import com.nimbletech.petadopt.common.Command;
import com.nimbletech.petadopt.appointment.dto.AppointmentDto;
import com.nimbletech.petadopt.appointment.dto.UpdateAppointmentStatusRequest;
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
public class UpdateAppointmentStatusService implements Command<UpdateAppointmentStatusRequest, AppointmentDto> {

    private final AppointmentRepository appointmentRepository;

    @Override
    public ResponseEntity<AppointmentDto> execute(UpdateAppointmentStatusRequest cmd) {
        log.info("Updating appointment with id={} to status={}", cmd.appointmentId(), cmd.updateAppointmentRequest().status());
        Appointment appointment = appointmentRepository.findById(cmd.appointmentId())
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        appointment.setStatus(cmd.updateAppointmentRequest().status());
        appointment = appointmentRepository.save(appointment);
        return ResponseEntity.ok(AppointmentMapper.toDto(appointment));
    }
}
