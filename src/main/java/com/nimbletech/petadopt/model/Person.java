package com.nimbletech.petadopt.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String address;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "person")
    private List<AdoptionRequest> adoptionRequests;

    public Person(Long id, String name, String email, UserStatus status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }
}
