package com.nimbletech.petadopt.pet.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Breed {
    // Dogs
    LABRADOR_RETRIEVER,
    GOLDEN_RETRIEVER,
    BULLDOG,
    HUSKY,
    BEAGLE,
    POODLE,
    
    // Cats
    TABBY,
    SIAMESE,
    PERSIAN,
    MAINE_COON,
    
    // Birds
    COCKATIEL,
    PARROT,
    PIGEON;

    public AnimalType getAnimalType() {
        return switch (this) {
            case LABRADOR_RETRIEVER, GOLDEN_RETRIEVER, BEAGLE, HUSKY, BULLDOG, POODLE -> AnimalType.DOG;
            case TABBY, SIAMESE, MAINE_COON, PERSIAN -> AnimalType.CAT;
            case COCKATIEL, PIGEON, PARROT -> AnimalType.BIRD;
        };
    }

    public boolean isValidFor(AnimalType type) {
        return getAnimalType() == type;
    }

}
