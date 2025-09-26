package com.nimbletech.petadopt.pet.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    private AnimalType type;
    private String breed;
    private String medicalHistory;
    private String microchipId;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private PetStatus status;


}

