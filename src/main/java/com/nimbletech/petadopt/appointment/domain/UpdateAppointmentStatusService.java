package com.nimbletech.petadopt.appointment.domain;

import com.nimbletech.petadopt.appointment.domain.models.AppointmentDto;
import com.nimbletech.petadopt.appointment.domain.models.UpdateAppointmentStatusRequest;
import com.nimbletech.petadopt.common.Command;
import com.nimbletech.petadopt.notification.domain.models.AppointmentStatusChangedEvent;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@Service
public class UpdateAppointmentStatusService implements Command<UpdateAppointmentStatusRequest, AppointmentDto> {

    private final AppointmentRepository appointmentRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public ResponseEntity<AppointmentDto> execute(UpdateAppointmentStatusRequest cmd) {
        log.info("Updating appointment with id={} to status={}", cmd.appointmentId(), cmd.updateAppointmentRequest().status());
        Appointment appointment = appointmentRepository.findById(cmd.appointmentId())
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        appointment.setStatus(cmd.updateAppointmentRequest().status());
        appointment = appointmentRepository.save(appointment);

        applicationEventPublisher.publishEvent(new AppointmentStatusChangedEvent(
                appointment.getId(),
                appointment.getUser().getId(),
                cmd.updateAppointmentRequest().status().name(),
                notificationMessageForStatus(cmd.updateAppointmentRequest().status(), appointment),
                LocalDateTime.now()
        ));
        return ResponseEntity.ok(AppointmentMapper.toDto(appointment));
    }

    private String notificationMessageForStatus(AppointmentStatus status, Appointment appointment) {
        return switch (status) {
            case PENDING -> throw new IllegalArgumentException("Cannot transition to PENDING");
            case CONFIRMED -> "✅ Your appointment on " + appointment.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("MMM dd, yyyy 'at' hh:mm a")) + " has been CONFIRMED!";
            case COMPLETED -> "🎉 Your appointment has been COMPLETED successfully!";
            case CANCELLED -> "❌ Your appointment has been CANCELLED.";
            case NO_SHOW -> "📅 You missed your appointment. Please schedule a new one if needed.";
        };
    }
}
