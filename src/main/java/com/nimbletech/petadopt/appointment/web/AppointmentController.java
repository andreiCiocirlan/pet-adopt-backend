package com.nimbletech.petadopt.appointment.web;

import com.nimbletech.petadopt.appointment.domain.*;
import com.nimbletech.petadopt.appointment.domain.models.AppointmentDto;
import com.nimbletech.petadopt.appointment.domain.models.AppointmentStatusRequest;
import com.nimbletech.petadopt.appointment.domain.models.CreateAppointmentRequest;
import com.nimbletech.petadopt.appointment.domain.models.UpdateAppointmentStatusRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final GetAppointmentsService getAppointmentsService;
    private final GetAppointmentsByUserIdService getAppointmentsByUserIdService;
    private final CreateAppointmentService createAppointmentService;
    private final UpdateAppointmentStatusService updateAppointmentStatusService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAllAppointments(
            @RequestParam(value = "status", required = false) List<AppointmentStatus> statuses) {
        return getAppointmentsService.execute(statuses);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByUserId(@PathVariable Long userId) {
        return getAppointmentsByUserIdService.execute(userId);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody CreateAppointmentRequest command) {
        return createAppointmentService.execute(command);
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{id}/status")
    public ResponseEntity<AppointmentDto> updateAppointmentStatus(@PathVariable Long id,
                                                                  @Valid @RequestBody AppointmentStatusRequest request) {
        return updateAppointmentStatusService.execute(new UpdateAppointmentStatusRequest(id, request));
    }

    @ExceptionHandler(AppointmentAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleAppointmentAlreadyExistsException(AppointmentAlreadyExistsException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
