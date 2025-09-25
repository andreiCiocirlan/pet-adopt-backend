package com.nimbletech.petadopt.adoption.model;

import com.nimbletech.petadopt.person.model.Person;
import com.nimbletech.petadopt.pet.model.Pet;
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
    private Person person;

    private LocalDateTime requestDate;

    @Enumerated(EnumType.STRING)
    private AdoptionStatus status;
}
