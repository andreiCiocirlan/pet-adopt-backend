package com.nimbletech.petadopt.appointment.repository;

import com.nimbletech.petadopt.appointment.model.Appointment;
import com.nimbletech.petadopt.appointment.model.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUserId(Long userId);
    List<Appointment> findByStatusIn(List<AppointmentStatus> statuses);
    long countByUserIdAndPetIdAndStatusIn(Long userId, String petId, List<AppointmentStatus> statuses);
}
