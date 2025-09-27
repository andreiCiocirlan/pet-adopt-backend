package com.nimbletech.petadopt.appointment.controller;

import com.nimbletech.petadopt.appointment.dto.AppointmentDto;
import com.nimbletech.petadopt.appointment.dto.CreateAppointmentCommand;
import com.nimbletech.petadopt.appointment.dto.UpdateAppointmentRequest;
import com.nimbletech.petadopt.appointment.dto.UpdateAppointmentStatusCommand;
import com.nimbletech.petadopt.appointment.service.CreateAppointmentService;
import com.nimbletech.petadopt.appointment.service.GetAppointmentsByUserIdService;
import com.nimbletech.petadopt.appointment.service.GetAppointmentsService;
import com.nimbletech.petadopt.appointment.service.UpdateAppointmentStatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final GetAppointmentsService getAppointmentsService;
    private final GetAppointmentsByUserIdService getAppointmentsByUserIdService;
    private final CreateAppointmentService createAppointmentService;
    private final UpdateAppointmentStatusService updateAppointmentStatusService;

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        return getAppointmentsService.execute(null);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByUserId(@PathVariable Long userId) {
        return getAppointmentsByUserIdService.execute(userId);
    }

    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody CreateAppointmentCommand command) {
        try {
            return createAppointmentService.execute(command);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<AppointmentDto> updateAppointmentStatus(@PathVariable("id") Long id,
                                                     @Valid @RequestBody UpdateAppointmentRequest request) {
        try {
            return updateAppointmentStatusService.execute(new UpdateAppointmentStatusCommand(id, request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
