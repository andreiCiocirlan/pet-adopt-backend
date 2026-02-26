package com.nimbletech.petadopt.appointment;

import com.nimbletech.petadopt.appointment.domain.Appointment;

public interface AppointmentApi {

    long count();

    Appointment save(Appointment appointment);

}
