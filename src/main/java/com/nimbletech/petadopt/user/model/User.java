package com.nimbletech.petadopt.user.model;

import com.nimbletech.petadopt.adoption.model.AdoptionRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String address;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "user")
    private List<AdoptionRequest> adoptionRequests;

    public User(Long id, String name, String email, UserStatus status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }
}
