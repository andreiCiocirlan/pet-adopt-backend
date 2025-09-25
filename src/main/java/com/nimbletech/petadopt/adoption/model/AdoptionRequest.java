package com.nimbletech.petadopt.adoption.model;

import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "adoption_requests")
public class AdoptionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Pet pet;

    @ManyToOne
    private User user;

    private LocalDateTime requestDate;

    @Enumerated(EnumType.STRING)
    private AdoptionStatus status;
}
