package com.nimbletech.petadopt;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModularityTest {
    static ApplicationModules modules = ApplicationModules.of(PetAdoptApplication.class);

    @Test
    void verifiesModularStructure() {
        modules.verify();
    }
}