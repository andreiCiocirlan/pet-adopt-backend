package com.nimbletech.petadopt.pet.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @ElementCollection
    @CollectionTable(name = "pet_image_urls", joinColumns = @JoinColumn(name = "pet_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();;

    @Enumerated(EnumType.STRING)
    private PetStatus status;


}

