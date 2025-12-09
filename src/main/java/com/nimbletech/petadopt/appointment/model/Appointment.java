package com.nimbletech.petadopt.appointment.model;

import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Pet pet;

    @ManyToOne
    private User user;

    private LocalDateTime appointmentDateTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Enumerated(EnumType.STRING)
    private AppointmentReason appointmentReason;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Appointment(Long id, Pet pet, User user, LocalDateTime appointmentDateTime, AppointmentStatus status, AppointmentReason appointmentReason) {
        this.id = id;
        this.pet = pet;
        this.user = user;
        this.appointmentDateTime = appointmentDateTime;
        this.status = status;
        this.appointmentReason = appointmentReason;
    }
}
