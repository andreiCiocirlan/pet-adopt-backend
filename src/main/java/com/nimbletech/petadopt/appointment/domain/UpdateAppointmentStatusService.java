package com.nimbletech.petadopt.appointment.domain;

import com.nimbletech.petadopt.common.Command;
import com.nimbletech.petadopt.appointment.domain.models.AppointmentDto;
import com.nimbletech.petadopt.appointment.domain.models.UpdateAppointmentStatusRequest;
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
