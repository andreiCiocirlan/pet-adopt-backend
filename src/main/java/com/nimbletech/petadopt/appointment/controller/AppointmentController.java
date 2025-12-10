package com.nimbletech.petadopt.appointment.controller;

import com.nimbletech.petadopt.appointment.dto.AppointmentDto;
import com.nimbletech.petadopt.appointment.dto.AppointmentStatusRequest;
import com.nimbletech.petadopt.appointment.dto.CreateAppointmentRequest;
import com.nimbletech.petadopt.appointment.dto.UpdateAppointmentStatusRequest;
import com.nimbletech.petadopt.appointment.model.AppointmentStatus;
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
    public ResponseEntity<List<AppointmentDto>> getAllAppointments(
            @RequestParam(value = "status", required = false) List<AppointmentStatus> statuses) {
        return getAppointmentsService.execute(statuses);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByUserId(@PathVariable Long userId) {
        return getAppointmentsByUserIdService.execute(userId);
    }

    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody CreateAppointmentRequest command) {
        try {
            return createAppointmentService.execute(command);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AppointmentDto> updateAppointmentStatus(@PathVariable Long id,
                                                                  @Valid @RequestBody AppointmentStatusRequest request) {
        try {
            return updateAppointmentStatusService.execute(new UpdateAppointmentStatusRequest(id, request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
