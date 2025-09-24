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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String type;
    private String breed;
    private String medicalHistory;
    private String microchipId;

    @Enumerated(EnumType.STRING)
    private PetStatus status;

    public Pet(Long id, String name, int age, String type, String breed, String medicalHistory, PetStatus status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.type = type;
        this.breed = breed;
        this.medicalHistory = medicalHistory;
        this.status = status;
    }

}

