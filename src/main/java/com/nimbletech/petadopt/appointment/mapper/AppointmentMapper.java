package com.nimbletech.petadopt.appointment.mapper;

import com.nimbletech.petadopt.appointment.dto.AppointmentDto;
import com.nimbletech.petadopt.appointment.dto.CreateAppointmentRequest;
import com.nimbletech.petadopt.appointment.model.Appointment;
import com.nimbletech.petadopt.appointment.model.AppointmentStatus;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.user.model.User;

public class AppointmentMapper {

    public static Appointment toEntity(CreateAppointmentRequest cmd, User user, Pet pet) {
        Appointment appointment = new Appointment();
        appointment.setPet(pet);
        appointment.setUser(user);
        appointment.setAppointmentDateTime(cmd.getAppointmentDateTime());
        appointment.setAppointmentReason(cmd.getAppointmentReason());
        AppointmentStatus appointmentStatus = cmd.getAppointmentStatus() != null ? cmd.getAppointmentStatus() : AppointmentStatus.PENDING;
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
