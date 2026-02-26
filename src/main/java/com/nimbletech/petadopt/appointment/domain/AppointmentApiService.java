package com.nimbletech.petadopt.appointment.domain;

import com.nimbletech.petadopt.appointment.AppointmentApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class AppointmentApiService implements AppointmentApi {

    private final AppointmentRepository appointmentRepository;

    @Override
    public long count() {
        return appointmentRepository.count();
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}
