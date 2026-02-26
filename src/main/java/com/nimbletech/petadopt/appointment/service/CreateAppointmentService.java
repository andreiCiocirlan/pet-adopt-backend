package com.nimbletech.petadopt.appointment.service;

import com.nimbletech.petadopt.common.Command;
import com.nimbletech.petadopt.appointment.dto.AppointmentDto;
import com.nimbletech.petadopt.appointment.dto.CreateAppointmentRequest;
import com.nimbletech.petadopt.appointment.exceptions.AppointmentAlreadyExistsException;
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

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateAppointmentService implements Command<CreateAppointmentRequest, AppointmentDto> {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Override
    public ResponseEntity<AppointmentDto> execute(CreateAppointmentRequest cmd) {
        log.info("Creating appointment for userId={} and petId={} on {} for reason: {}",
                cmd.userId(), cmd.petId(), cmd.appointmentDateTime(), cmd.appointmentReason());

        User user = userRepository.findById(cmd.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Pet pet = petRepository.findById(cmd.petId())
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));

        if (appointmentRepository.countByUserIdAndPetIdAndStatusIn(user.getId(), pet.getId(), List.of(AppointmentStatus.PENDING, AppointmentStatus.CONFIRMED)) > 0) {
            log.warn("Appointment already exists for {} and {}", pet.getName(), user.getName());
            throw new AppointmentAlreadyExistsException("Appointment already exists for " + pet.getName() + " and " + user.getName());
        }

        Appointment appointment = AppointmentMapper.toEntity(cmd, user, pet);
        appointment = appointmentRepository.save(appointment);
        return ResponseEntity.ok(AppointmentMapper.toDto(appointment));
    }
}
