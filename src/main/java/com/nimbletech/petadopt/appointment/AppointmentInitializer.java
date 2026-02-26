package com.nimbletech.petadopt.appointment;

import com.nimbletech.petadopt.appointment.domain.Appointment;
import com.nimbletech.petadopt.appointment.domain.AppointmentReason;
import com.nimbletech.petadopt.appointment.domain.AppointmentStatus;
import com.nimbletech.petadopt.pet.Pet;
import com.nimbletech.petadopt.pet.PetApi;
import com.nimbletech.petadopt.user.User;
import com.nimbletech.petadopt.user.UserApi;
import org.springframework.modulith.ApplicationModuleInitializer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AppointmentInitializer implements ApplicationModuleInitializer {
    private final AppointmentApi appointmentApi;
    private final PetApi petApi;
    private final UserApi userApi;

    public AppointmentInitializer(AppointmentApi appointmentApi,
                                  PetApi petApi,
                                  UserApi userApi) {
        this.appointmentApi = appointmentApi;
        this.petApi = petApi;
        this.userApi = userApi;
    }

    @Override
    public void initialize() {
        if (appointmentApi.count() == 0) {
            Pet max = petApi.findByName("Max").orElseThrow();
            Pet finn = petApi.findByName("Finn").orElseThrow();
            User alice = userApi.findByEmail("alice@example.com").orElseThrow();

            appointmentApi.save(new Appointment(null, max, alice,
                LocalDateTime.now().plusDays(5), AppointmentStatus.PENDING, AppointmentReason.MEET_AND_GREET));
            appointmentApi.save(new Appointment(null, finn, alice,
                LocalDateTime.now().plusDays(5), AppointmentStatus.CONFIRMED, AppointmentReason.MEET_AND_GREET));
        }
    }
}
