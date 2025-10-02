package com.nimbletech.petadopt.appointment.service;

import com.nimbletech.petadopt.Command;
import com.nimbletech.petadopt.appointment.dto.AppointmentDto;
import com.nimbletech.petadopt.appointment.dto.CreateAppointmentCommand;
import com.nimbletech.petadopt.appointment.mapper.AppointmentMapper;
import com.nimbletech.petadopt.appointment.model.Appointment;
import com.nimbletech.petadopt.appointment.model.AppointmentStatus;
import com.nimbletech.petadopt.appointment.repository.AppointmentRepository;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.pet.repository.PetRepository;
import com.nimbletech.petadopt.user.model.User;
import com.nimbletech.petadopt.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateAppointmentService implements Command<CreateAppointmentCommand, AppointmentDto> {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Override
    public ResponseEntity<AppointmentDto> execute(CreateAppointmentCommand cmd) {
        log.info("Creating appointment for userId={} and petId={} on {} for reason: {}",
                cmd.getUserId(), cmd.getPetId(), cmd.getAppointmentDate(), cmd.getAppointmentReason());

        User user = userRepository.findById(cmd.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Pet pet = petRepository.findById(cmd.getPetId())
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));

        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setPet(pet);
        appointment.setAppointmentDate(cmd.getAppointmentDate());
        appointment.setAppointmentReason(cmd.getAppointmentReason());
        appointment.setStatus(AppointmentStatus.PENDING);

        appointment = appointmentRepository.save(appointment);
        return ResponseEntity.ok(AppointmentMapper.toDto(appointment));
    }
}
