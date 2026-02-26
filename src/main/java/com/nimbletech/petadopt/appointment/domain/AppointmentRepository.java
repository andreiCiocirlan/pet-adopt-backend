package com.nimbletech.petadopt.appointment.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUserId(Long userId);
    List<Appointment> findByStatusIn(List<AppointmentStatus> statuses);
    long countByUserIdAndPetIdAndStatusIn(Long userId, String petId, List<AppointmentStatus> statuses);
}
