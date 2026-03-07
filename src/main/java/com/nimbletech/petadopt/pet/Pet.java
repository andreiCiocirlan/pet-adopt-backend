package com.nimbletech.petadopt.pet;

import com.nimbletech.petadopt.clinic.Clinic;
import com.nimbletech.petadopt.pet.domain.AnimalType;
import com.nimbletech.petadopt.pet.domain.PetStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
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
    private String characteristics;
    @ElementCollection
    @CollectionTable(name = "pet_image_urls", joinColumns = @JoinColumn(name = "pet_id"))
    @Column(name = "image_url")
    private Set<String> imageUrls = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private PetStatus status;

    private boolean isNeutered = false;
    private boolean hasMicrochip = false;
    private boolean isVaccinated = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    public Pet(String id, String name, int age, AnimalType type, String breed, String characteristics, Set<String> imageUrls, PetStatus status, Clinic clinic) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.type = type;
        this.breed = breed;
        this.characteristics = characteristics;
        this.imageUrls = imageUrls;
        this.status = status;
        this.clinic = clinic;
    }
}

