package com.nimbletech.petadopt.pet.dto;

import com.nimbletech.petadopt.pet.model.AnimalType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UpdatePetDto {
    private String name;
    private int age;
    private AnimalType type;
    private String breed;
    private String health;
    private String characteristics;
    private List<String> imageUrls;
}
