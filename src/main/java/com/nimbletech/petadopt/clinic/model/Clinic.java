package com.nimbletech.petadopt.clinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nimbletech.petadopt.pet.model.Pet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "clinics")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String address;

    private String phoneNumber;

    private double latitude;

    private double longitude;

    @JsonIgnore
    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Pet> pets;

}
