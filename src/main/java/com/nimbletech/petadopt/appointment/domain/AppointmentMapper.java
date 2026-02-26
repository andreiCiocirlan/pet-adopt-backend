package com.nimbletech.petadopt.appointment.domain;

import com.nimbletech.petadopt.appointment.domain.models.AppointmentDto;
import com.nimbletech.petadopt.appointment.domain.models.CreateAppointmentRequest;
import com.nimbletech.petadopt.pet.Pet;
import com.nimbletech.petadopt.user.User;

public class AppointmentMapper {

    public static Appointment toEntity(CreateAppointmentRequest cmd, User user, Pet pet) {
        Appointment appointment = new Appointment();
        appointment.setPet(pet);
        appointment.setUser(user);
        appointment.setAppointmentDateTime(cmd.appointmentDateTime());
        appointment.setAppointmentReason(cmd.appointmentReason());
        AppointmentStatus appointmentStatus = cmd.appointmentStatus() != null ? cmd.appointmentStatus() : AppointmentStatus.PENDING;
        appointment.setStatus(appointmentStatus);
        return appointment;
    }

    public static AppointmentDto toDto(Appointment appointment) {
        return new AppointmentDto(
                appointment.getId(),
                appointment.getPet().getId(),
                appointment.getUser().getId(),
                appointment.getAppointmentDateTime(),
                appointment.getStatus().name(),
                appointment.getAppointmentReason().name()
        );
    }
}
