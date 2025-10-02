package com.nimbletech.petadopt.appointment.mapper;

import com.nimbletech.petadopt.appointment.dto.AppointmentDto;
import com.nimbletech.petadopt.appointment.model.Appointment;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.user.model.User;

public class AppointmentMapper {

    public static Appointment toEntity(User user, Pet pet) {
        Appointment ar = new Appointment();
        ar.setPet(pet);
        ar.setUser(user);
        return ar;
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
